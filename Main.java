import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
        Layout layout= new Layout();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Breakoutball Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(layout);

	}

}