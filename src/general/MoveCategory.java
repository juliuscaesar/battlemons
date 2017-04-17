package general;

/**
 * MoveCategory Enum.
 * 
 * Each Move must belong to one of the following Categories: { Physical, Special, Status }.
 * 
 * Physical Moves are the ones where the Offensive Attribute used on the Damage Calculation are the { Attack }.
 * 
 * Special Moves are the ones where the Offensive Attribute used on the Damage Calculation are the { Special Attack }.
 * 
 * Status Moves are the ones where the Offensive Attribute used on the Damage Calculation is 0.
 * But they have 100% chance of Setting a Status on a Monster.
 */
public enum MoveCategory{
  Physical, Special, Status,
}