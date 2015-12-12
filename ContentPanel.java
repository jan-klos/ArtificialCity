
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
	private Timer tm = new Timer(1000, this);
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
		
	    Lane[][] streetTab = Util.createStreets(myGraph);
	    Lane[] street1 = streetTab[0];
	    Lane[] street2 = streetTab[1];
	    Lane[] street3 = streetTab[2];
	    
	    Person driver1 = new Person(50, null, null, myGraph.vertices.get(11), myGraph.vertices.get(16), null, null, null, null, null, true);
	    driver1.setPrevVertex(myGraph.vertices.get(28));
	    
		Vehicle auto2 = new Car(driver1);
		auto2.setStreet(street2);
		auto2.setLaneNr(0);
		auto2.setCurrentCell(auto2.getStreet()[0].cellList[0]);
		auto2.setSpeed(1);
		//auto2.curveLeft();
		vehicleList.add(auto2);
		myGraph.calcWeightedShortestPath((Car)auto2);
		
		Vehicle auto3 = new Car(null);
		auto3.setStreet(street1);
		auto3.setLaneNr(0);
		auto3.setCurrentCell(street1[0].cellList[0]);
		auto3.setSpeed(1);
		auto3.moveForward();
		//vehicleList.add(auto3);
		
		Vehicle bus1 = new Bus();
		bus1.setStreet(street1);
		bus1.setLaneNr(2);
		bus1.setCurrentCell(street1[2].cellList[0]);
		bus1.setSpeed(1);
		bus1.moveForward();
		//vehicleList.add(bus1);
		
		String[] linia8_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "right", "nowhere"};
		String[] linia8_2 = {"left", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "nowhere"};	
		
		String[] linia4_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "right", "forward", "forward", "nowhere"};
		String[] linia4_2 = {"forward", "forward", "right", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "nowhere"};
		
		String[] linia18 = {"forward", "forward", "forward", "forward", "nowhere"};
		
		Vehicle tram1 = new Tram(1, linia18, linia18);
		tram1.setStreet(street3);
		tram1.setLaneNr(0);
		tram1.setCurrentCell(street3[0].cellList[0]);
		tram1.setSpeed(1);
		((Tram)tram1).changeDirection();
		//vehicleList.add(tram1);
	
		tm.start();
		System.out.println(bus1 instanceof Bus);
		//System.out.println(Util.getDirection(street[0], myGraph.vertices.get(12)));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	    g.drawImage(bgImage, 0, 0, 1000, 1000, null);
	    
	    myGraph.paintEdges(g);
	    myGraph.paintVertices(g);
	    
	    for(Vehicle v : vehicleList) {
	    	v.paintVehicle(g, v.getColor());
	    }			
	}
	
	public void actionPerformed(ActionEvent e) {		
		Test.displayInfoAboutCar(vehicleList.get(0));
		//Test.displayInfoAboutCar(vehicleList.get(1));
		Movement.move(vehicleList);	
		counter++;
		repaint();
	}
	
	public static void drawCircle(int x, int y, int r, Graphics g) {
		//g.setColor(Color.RED);
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}