import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Slider extends JSlider implements ChangeListener {

	public Slider(int size) {
		// TODO Auto-generated constructor stub
		this.setMaximum(size/2);
		this.setMinimum(0);
		this.setPaintTicks(true);
		this.addChangeListener(this);
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
