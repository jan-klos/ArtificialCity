
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
		
	    Util.createStreets(myGraph);
		
		ArrayList<Person> personList = new ArrayList<Person>();
		Generator.generate(2, personList);
		
		for (Person person : personList)
		{
			if (person.getIsDriving())
			{
				vehicleList.add(Generator.generateCar(person));
			}
		}
		
		Person person1 = new Person(50, "", "", "", "", "", "", "", true);
		person1.setAllVertices(myGraph.vertices.get(97), myGraph.vertices.get(81), myGraph.vertices.get(101));
		vehicleList.add(Generator.generateCar(person1));
		
		
		
		//addPublicTransport();
		tm.start();
	}
	
	protected void addPublicTransport()
	{
		Vehicle tram1 = Generator.generatePubTran(8, 1); //numer linii, kierunek jazdy
		Vehicle tram2 = Generator.generatePubTran(8, 2);
		Vehicle tram3 = Generator.generatePubTran(4, 1);
		Vehicle tram4 = Generator.generatePubTran(4, 2);
		Vehicle tram5 = Generator.generatePubTran(18, 1);
		Vehicle tram6 = Generator.generatePubTran(18, 2);
		
		Vehicle bus1 = Generator.generatePubTran(159, 1);
		Vehicle bus2 = Generator.generatePubTran(159, 2);
		Vehicle bus3 = Generator.generatePubTran(173, 1);
		Vehicle bus4 = Generator.generatePubTran(173, 2);
		Vehicle bus5 = Generator.generatePubTran(179, 1);
		Vehicle bus6 = Generator.generatePubTran(179, 2);
		
		vehicleList.add(tram1);
		vehicleList.add(tram2);
		vehicleList.add(tram3);
		vehicleList.add(tram4);
		vehicleList.add(tram5);
		vehicleList.add(tram6);
		vehicleList.add(bus1);
		vehicleList.add(bus2);
		vehicleList.add(bus3);
		vehicleList.add(bus4);
		vehicleList.add(bus5);
		vehicleList.add(bus6);
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