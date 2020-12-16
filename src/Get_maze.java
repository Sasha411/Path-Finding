import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Get_maze extends JFrame implements ActionListener,ChangeListener {
	
	int size;
	int np;
	int x1;
	int y1;
	int x2;
	int y2;
	ArrayList<ArrayList<Node>> path = new ArrayList<ArrayList<Node>>();
	ArrayList<Node> nWalk = new ArrayList<Node>();
	
	JButton button = new JButton();
	JButton output = new JButton();
	JTextField jt = new JTextField();
	JTextField cp = new JTextField();
	JTextField sX = new JTextField();
	JTextField sY = new JTextField();
	JTextField tX = new JTextField();
	JTextField tY = new JTextField();
	
	JSlider slider = new JSlider(0,80);

	public Get_maze() {
		// TODO Auto-generated constructor stub
		button = new JButton("Enter");
		button.setBounds(200,100,100,50);
		button.setFocusable(false);
		button.addActionListener(this);
		output = new JButton("Output");
		output.setFocusable(false);
		output.addActionListener(this);
		//Panel
		JPanel panel = new JPanel();
		
		//Slider
		slider = new JSlider(0,80);
		slider.setPaintTicks(true);
		slider.addChangeListener(this);
		//label
		JLabel gS = new JLabel();
		gS.setText("Grid Size (Max Size:30)");
		
		JLabel stX = new JLabel();
		stX.setText("Start X ");
		
		JLabel stY = new JLabel();
		stY.setText("Start Y ");
		
		JLabel trX = new JLabel();
		trX.setText("Target X ");
		
		JLabel trY = new JLabel();
		trY.setText("Target Y ");
		
		JLabel nW = new JLabel();
		nW.setText("Closed Boxes ");
		//buttons
		sX = new JTextField();
		sY = new JTextField();
		tX = new JTextField();
		tY = new JTextField();
		sX.setPreferredSize(new Dimension(100,20));
		sY.setPreferredSize(new Dimension(100,20));
		tX.setPreferredSize(new Dimension(100,20));
		tY.setPreferredSize(new Dimension(100,20));
		jt = new JTextField();
		jt.setPreferredSize(new Dimension(100,20));
		
		this.setTitle("Maze Solver");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		this.setSize(800,600);
		this.setVisible(true);
		panel.add(gS);
		panel.add(jt);
		panel.add(nW);
		panel.add(slider);
		panel.add(stX);
		panel.add(sX);
		panel.add(stY);
		panel.add(sY);
		panel.add(trX);
		panel.add(tX);
		panel.add(trY);
		panel.add(tY);
		panel.add(button);
		panel.add(output);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button){
			size = Integer.parseInt(jt.getText());
			np = slider.getValue();
			x1 = Integer.parseInt(sX.getText());
			y1 = Integer.parseInt(sY.getText());
			x2 = Integer.parseInt(tX.getText());
			y2 = Integer.parseInt(tY.getText());
			Maze_algo algo = new Maze_algo(size,np,x1,y1,x2,y2);
			path = algo.solve();
		}
		if(e.getSource()==output){
			//Frame
			JFrame grid = new JFrame();
			grid.setTitle("Output");
			grid.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			grid.setSize(800, 600);
			grid.setVisible(true);
			grid.setLayout(new GridLayout(size,size));
			
			
			for(int i=1;i<=size;i++){
				for(int j=1;j<=size;j++){
					JButton cell = new JButton();
					if(i==x1 && j==y1){
						cell.setBackground(Color.blue);
						grid.add(cell);
						continue;
					}
					if(i==x2 && j==y2){
						cell.setBackground(Color.red);
						grid.add(cell);
						continue;
					}
					if(contains(i,j,path.get(0))){
						cell.setBackground(Color.green);
						grid.add(cell);
						continue;
					}
					else if(contains(i,j,path.get(1))){
						cell.setBackground(Color.BLACK);
						grid.add(cell);
						continue;
					}
					else{
						cell.setBackground(Color.WHITE);
						grid.add(cell);
					}
				}
			}
		}
	}

	private boolean contains(int i, int j, ArrayList<Node> path2) {
		// TODO Auto-generated method stub
		for(Node temp: path2){
			if(temp.x == i && temp.y== j){
				return true;
			}
		}
		return false;
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}