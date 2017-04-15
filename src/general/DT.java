package general;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;

import trainers.Trainer;

/**
 * Decision tree template for generalized AI.
 * 
 * DTs consist of a network of two types of nodes - Conditions, which execute
 * one of two child nodes based on how the underlying boolean condition
 * evaluates, and Behaviors, which return an object of the desired type. Both
 * Condition and Behavior are abstract classes; the exact boolean condition or
 * returned behavior (respectively) are determined in implementing subclasses.
 * 
 * DTs are loaded from a file that is formatted as a nested structure of
 * if-elses. The conditions and behaviors referenced in the file should clearly
 * correlate to a Condition_* or Behavior_* class somewhere in the DT
 * implementation (minus the prefixes).
 * 
 * if lines should consist of "if ", followed by a condition, and ending in a
 * colon; else lines should consist of "else:". Behaviors should get their own
 * line, and consist only of the name of the desired behavior. Leading and
 * trailing whitespace is to ignored.
 */
public class DT<T> {

    // The head of the tree.
    Node tree_head;

    /**
     * An abstract representation of a Node in this tree.
     */
    abstract class Node {

        // Simple name of this node. (Not containing Condition_* or Behavior_*
        // prefixes.
        String name;

        // Execute on this node.
        abstract T execute(Battle battle, Trainer user);

        // Convert this node back into a string representation.
        abstract String toString(String prefix);
    }

    /**
     * An abstract representation of a Behavior (or Leaf) in this tree.
     * Implementing subclasses will determine what the actual behavior of this
     * node is.
     */
    abstract class Behavior extends Node {

        // Constructor.
        Behavior() {
        }

        // Convert this node back into a string representation.
        String toString(String prefix) {
            return prefix + name;
        }
    }

    /**
     * An abstract representation of a Condition (or Branch) in this tree.
     * Implementing subclasses will determine what the actual condition of this
     * nodes is.
     */
    abstract class Condition extends Node {

        // Node executed on if this condition is true.
        Node true_child;
        // Node executed on if this condition is false.
        Node false_child;
        // Parameter used in some conditional comparisons
        float parameter;
        // Indication of whether or not the parameter variable is used; if not,
        // then the parameter should not be mutated or modified
        boolean uses_parameter;

        // Dead constructor.
        Condition() {
            true_child = null;
            false_child = null;
            parameter = 0;
        }
        
        // Constructor used for direct instantion (for testing).
        Condition(Node _true_child, Node _false_child, float _parameter) {
            true_child = _true_child;
            false_child = _false_child;
            parameter = _parameter;
        }

        // Constructor used when reading in trees. "br" should already be
        // opened.
        Condition(BufferedReader br, String class_prefix) throws IOException {
            true_child = parse_string(br, class_prefix);
            // skip "else:" line
            br.readLine();
            false_child = parse_string(br, class_prefix);
        }

        // Convert this node back into a string representation.
        String toString(String prefix) {
            return prefix + "if " + name + ":\n"
                    + true_child.toString(prefix + '\t') + '\n' + prefix
                    + "else:\n" + false_child.toString(prefix + '\t');
        }

        // The abstract function representing the actual boolean condition of
        // this node.
        abstract boolean check_condition(Battle battle, Trainer user);

        /**
         * Execution on this node. Checks if the condition evaluates to true or
         * false, and executes on the appropriate child node.
         */
        T execute(Battle battle, Trainer user) {
            if (check_condition(battle, user)) {
                System.out.println(name + " evaluated to true; executing on "
                        + true_child.name);
                return true_child.execute(battle, user);
            } else {
                System.out.println(name + " evaluated to false; executing on "
                        + false_child.name);
                return false_child.execute(battle, user);
            }
        }
    }

    /**
     * Constructor. Attempts to read in the file at filename, and then use
     * successive nested Condition and Behavior constructors, and instances of
     * parse_string() to build the tree out.
     */
    DT(String filename) {

        String class_prefix = this.getClass().getName() + "$";
        
        // Try to open this file.
        FileReader fr;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e1) {
            System.out.println("File not found for filename: " + filename);
            tree_head = null;
            return;
        }

        // Set up our reader.
        BufferedReader br = new BufferedReader(fr);
        try {
            // Build the actual tree.
            tree_head = parse_string(br, class_prefix);
        } catch (IOException e) {
            System.out.println("IOException");
            tree_head = null;
            return;
        }
    }

    /**
     * Parses the line that br is currently pointed at to determine if it
     * represents a Behavior or a Condition, and then passes br around as
     * necessary.
     * 
     * @param br
     *            A BufferedReader, pointed towards a file that contains a DT
     *            structure.
     * @return A new Condition or Behavior, parsed from the current line (and
     *         potentially, successive lines) in br.
     */
    @SuppressWarnings("unchecked")
    Node parse_string(BufferedReader br, String class_prefix) throws IOException {

        // Read in the new line and trim whitespace
        String to_parse = br.readLine();
        to_parse = to_parse.trim();
        String simple_name;

        // The return value
        Node ret = null;

        // If this is an else-case, parse successive lines as a Condition
        if (to_parse.substring(0, 3).equalsIgnoreCase("if ")) {

            // Knock off if clause and closing ":"
            to_parse = to_parse.substring(3, to_parse.length() - 1);
            simple_name = to_parse;

            // Append enclosing class prefix and Condition_ to transform into
            // canonical name of desired class
            to_parse = class_prefix + "Condition_" + to_parse;

            // Set up
            Class<?> condition_class = null;
            Constructor<?> ctor = null;

            // Try to interpret the string in the file as a
            // Condition class, and create a new object from that
            try {
                // Reflect condition string into a class name
                condition_class = Class.forName(to_parse);
                System.out.println(condition_class.getConstructors());
                // Create and execute constructors
                // FIXME idk why this works, but getConstructor() doesn't
                ctor = condition_class.getConstructors()[0];

                // Remember to pass in br!!
                ret = (Condition) ctor.newInstance(new Object[] { this, br , class_prefix});

            } catch (ClassNotFoundException e) {
                System.err.println("Error: condition name \"" + to_parse
                        + "\" does not refer " + "to a known class.");
                br.close();
                return null;
            } catch (Exception e) {
                System.err.println("Error: " + e.toString() + " "
                        + e.getMessage());
                br.close();
                return null;
            }

        } else { // Parse for behaviors

            simple_name = to_parse;

            // Append enclosing class prefix and Behavior_ to transform into
            // canonical name of desired class
            to_parse = class_prefix + "Behavior_" + to_parse;

            // Set up
            Class<?> behavior_class = null;
            Constructor<?> ctor = null;

            // Try to interpret the string in the file as a
            // Behavior class, and create a new object from that
            try {
                // Reflect condition string into a class name
                behavior_class = Class.forName(to_parse);
                System.out.println(behavior_class.getConstructors().length);
                // Create and execute constructors
                // FIXME idk why this works, but getConstructor() doesn't
                ctor = behavior_class.getConstructors()[0];
                ret = (Behavior) ctor.newInstance(new Object[] { this });

            } catch (ClassNotFoundException e) {
                System.err.println("Error: behavior name \"" + to_parse
                        + "\" does not refer " + "to a known class.");
                br.close();
                return null;
            } catch (Exception e) {
                System.err.println("Error: " + e.toString() + " "
                        + e.getMessage());
                br.close();
                return null;
            }

        }

        // Save the read-in name of this Node
        if (ret != null) ret.name = simple_name;
        return ret;
    }

    /**
     * Follow this tree until the desired behavior is returned.
     */
    public T getBehavior(Battle battle, Trainer user, long timeDue) {
        return tree_head.execute(battle, user);
    }

    /**
     * Get a String representation of this tree in a format that can be re-read
     * into new trees.
     */
    public String getTreeString() {
        return tree_head.toString("");
    }
}
