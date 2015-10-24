
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ContentPanel extends JPanel implements ActionListener {
	private Image bgImage = null;
	
	private Timer tm = new Timer(500, this);
	
	static int counter = 0;
	
	private Graph myGraph;
	
	private List<Vehicle> vehicleList = new ArrayList<Vehicle>();

	ContentPanel() {
		//setLayout(new BorderLayout());
		MediaTracker mt = new MediaTracker(this);
		bgImage = Toolkit.getDefaultToolkit().getImage("res/map.jpg");
		mt.addImage(bgImage, 0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myGraph = new Graph();
	    myGraph.init();
		
	    Collection<E> edges = myGraph.graph.getOutEdges(Graph.vertices.get(13));
	    LinkedList<E> le = new LinkedList(edges);
	    CellList[] cellListTab = new CellList[3];
	    
	    cellListTab = le.getFirst().cellTab;
			
		Vehicle auto1 = new Car(null);
		auto1.setCellListTab(le.get(0).cellTab);
		auto1.setCurrentListNr(1);
		auto1.setCurrentCell(le.get(0).cellTab[0].cellList[0]);
		auto1.setSpeed(1);
		auto1.curveLeft();
		vehicleList.add(auto1);
		
		/*Vehicle auto2 = new Car(null);
		auto2.setCellListTab(le.get(1).cellTab);
		auto2.setCurrentListNr(1);
		auto2.setCurrentCell(le.get(1).cellTab[0].cellList[0]);
		auto2.setSpeed(1);
		auto2.curveLeft();
		vehicleList.add(auto2);
		*/
		
		
		tm.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	    g.drawImage(bgImage, 0, 0, 1000, 1000, null);
	    
	    for(V v : myGraph.vertices) {
	    	g.setColor(Color.RED);
	    	this.drawCircle(v.getX(), v.getY(), 20, g);
			
	    	g.setColor(Color.BLACK);
			g.drawChars(v.toString().toCharArray(), 0,
						v.toString().length(), 
						v.getX()-6, 
						v.getY()+5);
		}
	    
	    for(Vehicle v :vehicleList) {
	    	v.paintVehicle(g);
	    }			
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Jednostka czasu: " + counter);
		//Test.displayInfoAboutCar(vehicleList.get(0));
		Movement.move(vehicleList);
		
		counter++;
		
		repaint();
	}
	
	private void drawCircle(int x, int y, int r, Graphics g) {
		//g.setColor(Color.RED);
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}