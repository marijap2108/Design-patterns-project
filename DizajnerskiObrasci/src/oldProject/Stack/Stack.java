package oldProject.Stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oldProject.Geometry.Point;
import oldProject.Geometry.Rectangle;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Stack extends JFrame {

	private JPanel contentPane;
	static DefaultListModel<Rectangle> stk= new DefaultListModel<>();  

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack frame = new Stack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Stack() {
		setTitle("Petrovic Marija IT69/2019");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnAddRectangle = new JButton("Add Rectangle");
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RectangleDialog dialog = new RectangleDialog();
				dialog.setVisible(true);
				if (dialog.isOk) {
					stk.add(0, dialog.GetData());
				}
			}
		});
		
		menuBar.add(btnAddRectangle);
		
		JButton btnDeleteRectangle = new JButton("Delete Rectangle");
		btnDeleteRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(stk.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "List is empty!");
				}
				else {
					RectangleDialog dialog = new RectangleDialog();
					Point p= stk.getElementAt(0).getUpperLeftPoint(); 
					int width = stk.getElementAt(0).getWidth();
					int height = stk.getElementAt(0).getHeight();
					
					dialog.SetData(p.getX(), p.getY(), width, height);
					dialog.setVisible(true);
					
					if(dialog.isOk)
					{
						stk.remove(0); 
					}
				}		
					
			}
		});
		menuBar.add(btnDeleteRectangle);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JList<Rectangle> listRectangle = new JList<Rectangle>();
		listRectangle.setModel(stk);
		contentPane.add(listRectangle, BorderLayout.WEST);
	}

}
