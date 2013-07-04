package datuak;

// Generated 25-feb-2013 18:35:59 by Hibernate Tools 3.4.0.CR1

/**
 * Totala generated by hbm2java
 */
public class Totala implements java.io.Serializable {

	private TotalaId id;
	private Partida partida;
	private Integer puntuak;
	private Integer t2Jaurtiak;
	private Integer t2Sartuak;
	private Integer t3Jaurtiak;
	private Integer t3Sartuak;
	private Integer t1Jaurtiak;
	private Integer t1Sartuak;
	private Integer rebDef;
	private Integer rebEras;
	private Integer asistentziak;
	private Integer galdutakoak;
	private Integer berreskuratutakoak;
	private Integer kontraerasoak;
	private Integer tapAld;
	private Integer tapKont;
	private Integer mateak;
	private Integer fpAld;
	private Integer fpKont;
	private Integer balorazioa;

	public Totala() {
	}

	public Totala(TotalaId id, Partida partida) {
		this.id = id;
		this.partida = partida;
	}

	public Totala(TotalaId id, Partida partida, Integer puntuak,
			Integer t2Jaurtiak, Integer t2Sartuak, Integer t3Jaurtiak,
			Integer t3Sartuak, Integer t1Jaurtiak, Integer t1Sartuak,
			Integer rebDef, Integer rebEras, Integer asistentziak,
			Integer galdutakoak, Integer berreskuratutakoak,
			Integer kontraerasoak, Integer tapAld, Integer tapKont,
			Integer mateak, Integer fpAld, Integer fpKont, Integer balorazioa) {
		this.id = id;
		this.partida = partida;
		this.puntuak = puntuak;
		this.t2Jaurtiak = t2Jaurtiak;
		this.t2Sartuak = t2Sartuak;
		this.t3Jaurtiak = t3Jaurtiak;
		this.t3Sartuak = t3Sartuak;
		this.t1Jaurtiak = t1Jaurtiak;
		this.t1Sartuak = t1Sartuak;
		this.rebDef = rebDef;
		this.rebEras = rebEras;
		this.asistentziak = asistentziak;
		this.galdutakoak = galdutakoak;
		this.berreskuratutakoak = berreskuratutakoak;
		this.kontraerasoak = kontraerasoak;
		this.tapAld = tapAld;
		this.tapKont = tapKont;
		this.mateak = mateak;
		this.fpAld = fpAld;
		this.fpKont = fpKont;
		this.balorazioa = balorazioa;
	}

	public TotalaId getId() {
		return this.id;
	}

	public void setId(TotalaId id) {
		this.id = id;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Integer getPuntuak() {
		return this.puntuak;
	}

	public void setPuntuak(Integer puntuak) {
		this.puntuak = puntuak;
	}

	public Integer getT2Jaurtiak() {
		return this.t2Jaurtiak;
	}

	public void setT2Jaurtiak(Integer t2Jaurtiak) {
		this.t2Jaurtiak = t2Jaurtiak;
	}

	public Integer getT2Sartuak() {
		return this.t2Sartuak;
	}

	public void setT2Sartuak(Integer t2Sartuak) {
		this.t2Sartuak = t2Sartuak;
	}

	public Integer getT3Jaurtiak() {
		return this.t3Jaurtiak;
	}

	public void setT3Jaurtiak(Integer t3Jaurtiak) {
		this.t3Jaurtiak = t3Jaurtiak;
	}

	public Integer getT3Sartuak() {
		return this.t3Sartuak;
	}

	public void setT3Sartuak(Integer t3Sartuak) {
		this.t3Sartuak = t3Sartuak;
	}

	public Integer getT1Jaurtiak() {
		return this.t1Jaurtiak;
	}

	public void setT1Jaurtiak(Integer t1Jaurtiak) {
		this.t1Jaurtiak = t1Jaurtiak;
	}

	public Integer getT1Sartuak() {
		return this.t1Sartuak;
	}

	public void setT1Sartuak(Integer t1Sartuak) {
		this.t1Sartuak = t1Sartuak;
	}

	public Integer getRebDef() {
		return this.rebDef;
	}

	public void setRebDef(Integer rebDef) {
		this.rebDef = rebDef;
	}

	public Integer getRebEras() {
		return this.rebEras;
	}

	public void setRebEras(Integer rebEras) {
		this.rebEras = rebEras;
	}

	public Integer getAsistentziak() {
		return this.asistentziak;
	}

	public void setAsistentziak(Integer asistentziak) {
		this.asistentziak = asistentziak;
	}

	public Integer getGaldutakoak() {
		return this.galdutakoak;
	}

	public void setGaldutakoak(Integer galdutakoak) {
		this.galdutakoak = galdutakoak;
	}

	public Integer getBerreskuratutakoak() {
		return this.berreskuratutakoak;
	}

	public void setBerreskuratutakoak(Integer berreskuratutakoak) {
		this.berreskuratutakoak = berreskuratutakoak;
	}

	public Integer getKontraerasoak() {
		return this.kontraerasoak;
	}

	public void setKontraerasoak(Integer kontraerasoak) {
		this.kontraerasoak = kontraerasoak;
	}

	public Integer getTapAld() {
		return this.tapAld;
	}

	public void setTapAld(Integer tapAld) {
		this.tapAld = tapAld;
	}

	public Integer getTapKont() {
		return this.tapKont;
	}

	public void setTapKont(Integer tapKont) {
		this.tapKont = tapKont;
	}

	public Integer getMateak() {
		return this.mateak;
	}

	public void setMateak(Integer mateak) {
		this.mateak = mateak;
	}

	public Integer getFpAld() {
		return this.fpAld;
	}

	public void setFpAld(Integer fpAld) {
		this.fpAld = fpAld;
	}

	public Integer getFpKont() {
		return this.fpKont;
	}

	public void setFpKont(Integer fpKont) {
		this.fpKont = fpKont;
	}

	public Integer getBalorazioa() {
		return this.balorazioa;
	}

	public void setBalorazioa(Integer balorazioa) {
		this.balorazioa = balorazioa;
	}

}
