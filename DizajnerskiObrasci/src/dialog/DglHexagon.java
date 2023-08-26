package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatter;

import adapter.HexagonAdapter;
import model.shape.Point;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DglHexagon extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner textFieldX;
	private JSpinner textFieldY;
	private JSpinner textFieldRadius;
	private JButton btnInnerColor;
	private JButton btnOutlineColor;
	public boolean isOk;

	public static void main(String[] args) {
		try {
			DglHexagon dialog = new DglHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SetData(int x, int y) {
		textFieldX.setValue(x);
		textFieldY.setValue(y);
		
		textFieldX.setEnabled(false);
		textFieldY.setEnabled(false);
	}
	
	public void SetData(HexagonAdapter hexagon) {
		textFieldX.setValue(hexagon.getX());
		textFieldY.setValue(hexagon.getY());
		textFieldRadius.setValue(hexagon.getRadius());
		btnInnerColor.setBackground(hexagon.getInnerColor());
		btnOutlineColor.setBackground(hexagon.getColor());
	
	}
	
	public HexagonAdapter getHexagon() {
		return new HexagonAdapter(new Point((Integer) textFieldX.getValue(),(Integer) textFieldY.getValue()), (Integer) textFieldRadius.getValue(), false, btnInnerColor.getBackground(), btnOutlineColor.getBackground());
	}

	public DglHexagon() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[50%][50%]", "[24px][14px][14px][14px][14px][]"));
		{
			JLabel lblHexagon = new JLabel("Hexagon");
			lblHexagon.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblHexagon, "cell 0 0");
		}
		{
			JLabel lblX = new JLabel("X coordinate of center");
			contentPanel.add(lblX, "cell 0 1,alignx center,aligny center");
		}
		{
			textFieldX = new JSpinner();
			contentPanel.add(textFieldX, "cell 1 1,growx,aligny center");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldX.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblY = new JLabel("Y coordinate of center");
			contentPanel.add(lblY, "cell 0 2,alignx center,aligny center");
		}
		{
			textFieldY = new JSpinner();
			contentPanel.add(textFieldY, "cell 1 2,growx,aligny center");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldY.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblRadius = new JLabel("Radius");
			contentPanel.add(lblRadius, "cell 0 3,alignx center,aligny center");
		}
		{
			textFieldRadius = new JSpinner();
			contentPanel.add(textFieldRadius, "cell 1 3,growx,aligny center");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldRadius.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			btnInnerColor = new JButton("Inner color");
			btnInnerColor.setOpaque(true);
			btnInnerColor.setBorderPainted(false);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
					if (innerColor != null)
						btnInnerColor.setBackground(innerColor);
				}
			});
			contentPanel.add(btnInnerColor, "cell 0 4,alignx center,aligny center");
		}
		{
			btnOutlineColor = new JButton("Outline color");
			btnOutlineColor.setOpaque(true);
			btnOutlineColor.setBorderPainted(false);
			btnOutlineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColor.getBackground());
					if (outlineColor != null)
						btnOutlineColor.setBackground(outlineColor);
				}
			});
			contentPanel.add(btnOutlineColor, "cell 0 5,alignx center,aligny center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (textFieldRadius.getValue() != null && textFieldX.getValue() != null && textFieldY.getValue() != null) {
							isOk = true;
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Please, fill in a form!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void SetData(int x, int y, Color innerColor, Color outlineColor) {
		SetData(x, y);
		btnInnerColor.setBackground(innerColor);
		btnOutlineColor.setBackground(outlineColor);
		btnInnerColor.setEnabled(false);
		btnOutlineColor.setEnabled(false);
	}
}
