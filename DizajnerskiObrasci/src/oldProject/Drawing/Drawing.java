package oldProject.Drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import oldProject.Geometry.Circle;
import oldProject.Geometry.Donut;
import oldProject.Geometry.Line;
import oldProject.Geometry.Point;
import oldProject.Geometry.Rectangle;

import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;

public class Drawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public boolean select;
	public String selectedShape;
	private JToggleButton btnSelect;
	private PnlDrawing panel = new PnlDrawing(this);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drawing frame = new Drawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Drawing() {
		setTitle("Petrovic Marija IT69/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnDrawing = new JButton("Drawing");
		btnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<String> Drawing = new JComboBox<String>();
				Drawing.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						handleShape((String) Drawing.getSelectedItem());
					}
				});
				menuBar.add(Drawing, 1);
				Drawing.insertItemAt("Point", 0);
				Drawing.insertItemAt("Line", 1);
				Drawing.insertItemAt("Rectangle", 2);
				Drawing.insertItemAt("Circle", 3);
				Drawing.insertItemAt("Donut", 4);
				btnDrawing.setVisible(false);
				JButton btnBack = new JButton("Back");
				menuBar.add(btnBack, 0);
				btnBack.addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent arg0) {
						btnDrawing.setVisible(true);
						Drawing.setVisible(false);
						btnBack.setVisible(false);
					}
				} );
			}
		});
		menuBar.add(btnDrawing);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(panel, BorderLayout.CENTER);
		
		btnSelect = new JToggleButton("Select");
		btnSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				toggleSelect();
			}
		});
		menuBar.add(btnSelect);
		
				
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getSelectedShape() != null) {
					modify();
					panel.getSelectedShape().setSelected(false);

				} else {
					JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
					btnSelect.setSelected(true);
				}
				panel.setSelectedShape(null);
				btnSelect.setSelected(false);
			}
		});
		menuBar.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
				btnSelect.setSelected(false);
			}
		});
		menuBar.add(btnDelete);
	}
	
	protected void delete() {

		oldProject.Geometry.Shape selectedShape = panel.getSelectedShape();

		if (selectedShape != null) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				panel.getShapes().remove(selectedShape);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error", JOptionPane.WARNING_MESSAGE);
		}
		panel.setSelectedShape(null);
		panel.repaint();
	}
	
	private void modify() {
		
		if (selectedShape == null)  {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		switch(panel.getSelectedShape().getClass().getSimpleName()) {
			case "Point":
				DglPoint p = new DglPoint();
				p.SetData((Point) (panel.getSelectedShape()));
				p.setVisible(true);
				if (p.isOk) {					
					panel.getShapes().add(p.getPoint());
				}
				break;
			case "Line":
				DglLine l = new DglLine();
				l.SetData((Line) (panel.getSelectedShape()));
				l.setVisible(true);
				if (l.isOk) {					
					panel.getShapes().add(l.getLine());
				}
				break;
			case "Rectangle":
				DglRectangle r = new DglRectangle();
				r.SetData((Rectangle) (panel.getSelectedShape()));
				r.setVisible(true);
				if (r.isOk) {					
					panel.getShapes().add(r.getRectangle());
				}
				break;
			case "Circle":
				DglCircle c = new DglCircle();
				c.SetData((Circle) (panel.getSelectedShape()));
				c.setVisible(true);
				if (c.isOk) {					
					panel.getShapes().add(c.getCircle());
				}
				break;
			case "Donut":
				DglDonut d = new DglDonut();
				d.SetData((Donut) (panel.getSelectedShape()));
				d.setVisible(true);
				if (d.isOk) {					
					panel.getShapes().add(d.getDonut());
				}
				break;
			default:
				return;
		}
		
		panel.getShapes().remove(panel.getSelectedShape());
		repaint();
	}
	
	public String getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(String selectedShape) {
		this.selectedShape = selectedShape;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}
	
	private void toggleSelect() {
		this.setSelect(!isSelect());
	}
	
	private void handleShape(String shape) {
		this.setSelectedShape(shape);
	}
	
}
