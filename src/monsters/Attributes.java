package monsters;

import general.Status;

public class Attributes {
	private final int dft_atk;
	private final int dft_spAtk;
	private final int dft_def;
	private final int dft_spDef;
	private final int dft_speed;
	private final int dft_acc;
	
	private final double[] buff = {1, 1.5, 2, 2.5, 3, 3.5, 4};
	private final double[] debuff = {1, 0.66, 0.5, 0.4, 0.33, 0.28, 0.25};
	
	private final static int maxStage = 6;
	
	private int atkStage;
	private int spAtkStage;
	private int spDefStage;
	private int defStage;
	private int spdStage;
	private int dodgeStage;
	private int accStage;
	private int criticalStage;
	
	public Attributes(int atk, int spAtk, int spDef, int def, int spd){
		this.dft_atk = atk;
		this.dft_spAtk = spAtk;
		this.dft_spDef = spDef;
		this.dft_def = def;
		this.dft_speed = spd;
		this.dft_acc = 100;
	}
	
	public void de_buff_atk(boolean buff) {
		if(atkStage == maxStage || atkStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			atkStage++;
		}
		else{
			atkStage--;
		}
	}
	
	public void de_buff_spAtk(boolean buff) {
		if(spAtkStage == maxStage || spAtkStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			spAtkStage++;
		}
		else{
			spAtkStage--;
		}
	}
	
	public void de_buff_def(boolean buff) {
		if(defStage == maxStage || defStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			defStage++;
		}
		else{
			defStage--;
		}
	}
	
	public void de_buff_spDef(boolean buff) {
		if(spDefStage == maxStage || spDefStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			spDefStage++;
		}
		else{
			spDefStage--;
		}
	}
	
	public void de_buff_spd(boolean buff) {
		if(spdStage == maxStage || spdStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			spdStage++;
		}
		else{
			spdStage--;
		}
	}
	
	public void de_buff_dodge(boolean buff) {
		if(dodgeStage == maxStage || dodgeStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			dodgeStage++;
		}
		else{
			dodgeStage--;
		}
	}
	
	public void de_buff_acc(boolean buff) {
		if(accStage == maxStage || accStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			accStage++;
		}
		else{
			accStage--;
		}
	}
	
	public void de_buff_crit(boolean buff) {
		if(criticalStage == maxStage || criticalStage == -maxStage){
			throw new IllegalArgumentException("Max Stage");
		}
		if(buff) {
			criticalStage++;
		}
		else{
			criticalStage--;
		}
	}
	
	public void reset() {
		atkStage = 0;
		defStage = 0;
		spAtkStage = 0;
		spDefStage = 0;
		spdStage = 0;
		dodgeStage = 0;
		accStage = 0;
		criticalStage = 0;
	}
	
	int getAtk(){
		double multi = 1;
		if(atkStage > 0){
			multi = buff[atkStage];
		}
		else{
			multi = debuff[atkStage];
		}
		return (int)((double)dft_atk * multi);
	}
	
	int getSpAtk(){
		double multi = 1;
		if(spAtkStage > 0){
			multi = buff[spAtkStage];
		}
		else{
			multi = debuff[spAtkStage];
		}
		return (int)((double)dft_spAtk * multi);
	}
	
	int getSpDef(){
		double multi = 1;
		if(spDefStage > 0){
			multi = buff[spDefStage];
		}
		else{
			multi = debuff[spDefStage];
		}
		return (int)((double)dft_spDef * multi);
	}
	
	int getDef(){
		double multi = 1;
		if(defStage > 0){
			multi = buff[defStage];
		}
		else{
			multi = debuff[defStage];
		}
		return (int)((double)dft_def * multi);
	}
	
	int getSpd(Status stat){
		double multi = 1;
		if(defStage > 0){
			multi = buff[spdStage];
		}
		else{
			multi = debuff[spdStage];
		}
		double spd = (double)dft_speed * multi;
		switch(stat){
			case Paralysis: {
				return (int)(spd / 4);
			}
			default: {
				return (int)spd;
			}
		}
	}
	
	int getDodge(){
		double multi = 1;
		if(dodgeStage < 0){
			multi = buff[dodgeStage];
		}
		else{
			multi = debuff[dodgeStage];
		}
		return (int)(100.0 * multi);
	}
	
	int getAcc(){
		double multi = 1;
		if(accStage < 0){
			multi = buff[accStage];
		}
		else{
			multi = debuff[accStage];
		}
		return (int)(100.0 * multi);
	}
	
	int getCriticalChance(){
		return 0;
	}
	
}
