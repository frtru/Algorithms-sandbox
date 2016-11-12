package core.utils;

public class Position {
	public Position(int a_X, int a_Y) {
		m_X = a_X; m_Y = a_Y;
	}
			
	public int getX()	{ return m_X; }
	public int getY()	{ return m_Y; }
	
	public void setX(int a_X) { m_X = a_X; }
	public void setY(int a_Y) { m_Y = a_Y; }	
	public void set(int a_X, int a_Y) { m_X = a_X; m_Y = a_Y;}

	public void print() {
		System.out.println("Position -> X:" + m_X + ", Y:" + m_Y);
	}

	private int m_X, m_Y;
}
