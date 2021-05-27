package Pck_Game;
import java.util.ArrayList;
import javax.swing.Timer;

public class Aliado extends Personagens{
	private ArrayList<Ataque> ataque = new ArrayList<Ataque>();
	private boolean toConstruct = false;
	private boolean toAtaca;
	private String tipoAliado;
	
	public Aliado(int x, int y, int vida, String nomeAliado, boolean b) {
		super(x, y, vida, "Aliados", "sAliado" + nomeAliado);	
		tipoAliado = nomeAliado;
		toAtaca = b;
	}

	public boolean isToConstruct() { return toConstruct; }
	public void    setToConstruct(boolean toConstruct) { this.toConstruct = toConstruct; }
	
	public void addAtaque(int[] positionInd, String aliado) { // ADICIONA O ATAQUE DO ALIADO
		if(this.getY() > 280 && toConstruct == true) {
			int y = (this.getY()-280)/100;
			if (positionInd[y] > 0) {
				ataque.add(new Ataque(this.getX()+this.getWidth(),this.getY()+this.getHeight()/2, aliado));
			}
		}
	}
	public void addAtaque(String vazio, String aliado) {  }

	public ArrayList<Ataque> getAtaque() { return ataque; }
	public void setAtaque(ArrayList<Ataque> ataque) { this.ataque = ataque; }
	
	public String getTipoAliado() { return tipoAliado; }
	
	public boolean getToAtaca() { return toAtaca; }
}
