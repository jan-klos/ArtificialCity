import java.util.LinkedList;

// Edge class

public class E {
	private V begin;
	private V end;
	private int length;
	private int speedLimit;
	private int lanesNum;
	public CellList[] cellTab;
	private int cellsNum;
	
	public static final int CELL_SIZE = 5;
	
	E (V _begin, V _end, int _length, int _speedLimit, int _lanesNum) {
		this.begin = _begin;
		this.end = _end;
		this.length = (int) Math.sqrt((end.getX()-begin.getX()) * (end.getX()-begin.getX()) +
				            (end.getY()-begin.getY()) * (end.getY()-begin.getY()));
		
		//System.out.println(begin.toString() + " - " + end.toString() + " : " + length);
		this.speedLimit = _speedLimit;
		this.lanesNum = _lanesNum;
		
		cellsNum =  length / CELL_SIZE;
		cellTab = new CellList[lanesNum];
		
		for(int i = 0; i < lanesNum; i++) {
			
			
			if(i == 0) { // 1 pasy
				cellTab[i] = new CellList(cellsNum, this.begin, this.end, 0, 2, 0);
				for(int j = 0; j < cellTab[i].cellList.length; j++) {
					cellTab[i].cellList[j].setX(cellTab[i].cellList[j].getX() -4);
					cellTab[i].cellList[j].setY(cellTab[i].cellList[j].getY() - 4);
				}				
			}
			
			if(i == 1) { // 2 pasy
				cellTab[i] = new CellList(cellsNum, this.begin, this.end, 0, 1, 1);				
			}
			
			if(i == 2) { // 3 pasy
				cellTab[i] = new CellList(cellsNum, this.begin, this.end, 0, 0, 2);
				for(int j = 0; j < cellTab[i].cellList.length; j++) {
					cellTab[i].cellList[j].setX(cellTab[i].cellList[j].getX() + 4);
					cellTab[i].cellList[j].setY(cellTab[i].cellList[j].getY() + 4);
				}
			}
		}
		
		/*switch (lanesNum) {
			case 1: cellTab[0] = new CellList(cellsNum, this.begin, this.end, 0, -1, -1);
					break;
			
			case 2: cellTab[0] = new CellList(cellsNum, this.begin, this.end, 0, -1, -1);
			        cellTab[1] = new CellList(cellsNum, this.begin, this.end, 1, -1, -1);
				    break;
			
			case 3: cellTab[0] = new CellList(cellsNum, this.begin, this.end,0, -1, -1);
			 		cellTab[1] = new CellList(cellsNum, this.begin, this.end,1, -1, -1);
			 		cellTab[2] = new CellList(cellsNum, this.begin, this.end,2, -1, -1);
					break;
										
		}	*/	
	}
	
	public int getLength() {
		return this.length;
	}
	
	public String toString() {
		return this.begin.toString() + " - " + this.end.toString();
	}
	
	public int getBeginX() {
		return begin.getX();
	}
	
	public int getBeginY() {
		return begin.getY();
	}
	
	public int getEndX() {
		return end.getX();
	}
	
	public int getEndY() {
		return end.getY();
	}
	
	public int getLanesNum() {
		return lanesNum;
	}
	
	public String cellsToString() {
		String output = "";
		for(int i = 0; i < lanesNum; i++) {
			for(int j = 0; j < cellsNum; j++) {
				output += cellTab[i].cellList[j].toString();
			}
		output += "\n------------------------------\n";
		}
		
		return output;
	}
	
}
