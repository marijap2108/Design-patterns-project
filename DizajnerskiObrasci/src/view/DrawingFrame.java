package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.DrawingController;
import model.shape.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.TextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class DrawingFrame extends JFrame implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	public String selectedShape;
	private TextArea textAreaLogs;
	private JToggleButton btnSelect;
	private JButton btnDelete;
	private JButton btnModify;
	public boolean select;
	public JMenuItem mntmUndo;
	public JMenuItem mntmRedo;
	public Color innerColor = Color.BLACK;
	public Color outlineColor = Color.WHITE;
	private JRadioButton rdbtnPoint;

	public void log(String log) {
		textAreaLogs.append(log + "\n");
	}

	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.handleClick(e);
			}
		});

		getContentPane().add(view, BorderLayout.CENTER);

		setTitle("Petrovic Marija IT69/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 410);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmImport = new JMenuItem("Import file");
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.importFile();
			}
		});
		mnFile.add(mntmImport);

		JMenuItem mntmExport = new JMenuItem("Export file");
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.exportFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmExport);

		JMenuItem mntmImportDrawing = new JMenuItem("Import Drawing");
		mntmImportDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.importDrawing();
			}
		});
		mnFile.add(mntmImportDrawing);

		JMenuItem mntmExportDrawing = new JMenuItem("Export Drawing");
		mntmExportDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.exportDrawing();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmExportDrawing);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setEnabled(false);
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		mnEdit.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setEnabled(false);
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});
		mnEdit.add(mntmRedo);

		JMenu mnLayer = new JMenu("Layer");
		menuBar.add(mnLayer);

		JMenuItem mntmToFront = new JMenuItem("To Front");
		mntmToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toFront();
			}
		});
		mnLayer.add(mntmToFront);

		JMenuItem mntmToBack = new JMenuItem("To Back");
		mntmToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toBack();
			}
		});
		mnLayer.add(mntmToBack);

		JMenuItem mntmBringToFront = new JMenuItem("Bring To Front");
		mntmBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToFront();
			}
		});
		mnLayer.add(mntmBringToFront);

		JMenuItem mntmBringToBack = new JMenuItem("Bring To Back");
		mntmBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToBack();
			}
		});
		mnLayer.add(mntmBringToBack);

		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		view.setLayout(new BorderLayout(0, 0));

		textAreaLogs = new TextArea();
		textAreaLogs.setEditable(false);
		view.add(textAreaLogs, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		view.add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		rdbtnPoint = new JRadioButton("Point");
		rdbtnPoint.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnPoint.isSelected())
					handleShape("Point");
			}
		});
		panel.add(rdbtnPoint);

		JRadioButton rdbtnLine = new JRadioButton("Line");
		rdbtnLine.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnLine.isSelected())
					handleShape("Line");
			}
		});
		panel.add(rdbtnLine);

		JRadioButton rdbtnRectangle = new JRadioButton("Rectangle");
		rdbtnRectangle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnRectangle.isSelected())
					handleShape("Rectangle");
			}
		});
		panel.add(rdbtnRectangle);

		JRadioButton rdbtnCircle = new JRadioButton("Circle");
		rdbtnCircle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnCircle.isSelected())
					handleShape("Circle");
			}
		});
		panel.add(rdbtnCircle);

		JRadioButton rdbtnDonut = new JRadioButton("Donut");
		rdbtnDonut.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnDonut.isSelected())
					handleShape("Donut");
			}
		});
		panel.add(rdbtnDonut);

		JRadioButton rdbtnHexagon = new JRadioButton("Hexagon");
		rdbtnHexagon.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnHexagon.isSelected())
					handleShape("Hexagon");
			}
		});
		panel.add(rdbtnHexagon);

		ButtonGroup SelectButtons = new ButtonGroup();

		SelectButtons.add(rdbtnPoint);
		SelectButtons.add(rdbtnLine);
		SelectButtons.add(rdbtnRectangle);
		SelectButtons.add(rdbtnCircle);
		SelectButtons.add(rdbtnDonut);
		SelectButtons.add(rdbtnHexagon);

		btnSelect = new JToggleButton("Select");
		btnSelect.addChangeListener(new ChangeListener() {
			private boolean select;

			public void stateChanged(ChangeEvent arg0) {
				toggleSelect();
			}

			public boolean isSelect() {
				return select;
			}

			public void setSelected(boolean select) {
				this.select = select;
				setSelect(select);
			}

			private void toggleSelect() {
				this.setSelected(!isSelect());
			}
		});
		menuBar.add(btnSelect);

		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modifyMethod(arg0);
			}
		});
		menuBar.add(btnModify);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteMethod(arg0);
				btnSelect.setSelected(false);
			}
		});
		menuBar.add(btnDelete);

		JButton btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setBackground(innerColor);
		btnInnerColor.setForeground(Color.WHITE);
		btnInnerColor.setOpaque(true);
		btnInnerColor.setBorderPainted(false);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
				if (innerColor != null)
					btnInnerColor.setBackground(innerColor);

				Color textColor = Color.getColor("", ~(btnInnerColor.getBackground().getRGB() & 0xFFFFFF));
				btnInnerColor.setForeground(textColor);
			}
		});
		menuBar.add(btnInnerColor);

		JButton btnOutlineColor = new JButton("Outline Color");
		btnOutlineColor.setBackground(outlineColor);
		btnOutlineColor.setOpaque(true);
		btnOutlineColor.setBorderPainted(false);
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outlineColor = JColorChooser.showDialog(null, "Choose inner color", btnOutlineColor.getBackground());
				if (outlineColor != null)
					btnOutlineColor.setBackground(outlineColor);
				Color textColor = Color.getColor("", ~(btnOutlineColor.getBackground().getRGB() & 0xFFFFFF));
				btnOutlineColor.setForeground(textColor);
			}
		});
		menuBar.add(btnOutlineColor);
	}

	public DrawingView getView() {
		return view;
	}

	public JToggleButton getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(JToggleButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	private void handleShape(String shape) {
		this.selectedShape = shape;
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

	public String getLogs() {
		return textAreaLogs.getText();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selectedShape")) {
			if (((ArrayList<Shape>) evt.getNewValue()).isEmpty()) {
				btnDelete.setEnabled(false);
				btnModify.setEnabled(false);
			} else {
				btnDelete.setEnabled(true);
				if (((ArrayList<Shape>) evt.getNewValue()).size() == 1) {
					btnModify.setEnabled(true);
				} else {
					btnModify.setEnabled(false);
				}
			}
		}

	}

}
