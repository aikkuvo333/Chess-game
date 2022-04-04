package dao;

import java.util.ArrayList;
import java.util.List;

public class PelaajaTilasto extends Pelaaja{
	private int voitot;
	private List<PelinTiedot> pelit;
	
	public void setPelit(List<PelinTiedot> pelit){
		this.pelit = pelit;
		voitot = 0;
		for(PelinTiedot peli: this.pelit) {
			if(peli.getVoittaja().getPelaajaId() == super.getPelaajaId()) {
				voitot++;
			}
		}
	}
	
	public int getVoittoprosentti() {
		return voitot/pelit.size();
	}
	
	public int getVoitot() {
		return voitot;
	}
	
	public int getPeleja() {
		return pelit.size();
	}
	
	public List<PelinTiedot> getPelit(){
		return pelit;
	}
	
	
}
