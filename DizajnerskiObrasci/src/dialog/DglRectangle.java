package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatter;

import model.shape.Point;
import model.shape.Rectangle;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DglRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner textFieldX;
	private JSpinner textFieldY;
	private JSpinner textFieldWidth;
	private JSpinner textFieldHeight;
	private JButton btnInnerColor;
	private JButton btnOutlineColor;
	public boolean isOk;

	public static void main(String[] args) {
		try {
			DglRectangle dialog = new DglRectangle();
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
	
	public void SetData(Rectangle rectangle) {
		textFieldX.setValue(rectangle.getUpperLeftPoint().getX());
		textFieldY.setValue(rectangle.getUpperLeftPoint().getY());
		textFieldWidth.setValue(rectangle.getWidth());
		textFieldHeight.setValue(rectangle.getHeight());
		btnInnerColor.setBackground(rectangle.getInnerColor());
		btnOutlineColor.setBackground(rectangle.getColor());
		
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(new Point((Integer) textFieldX.getValue(), (Integer) textFieldY.getValue()), (Integer) textFieldWidth.getValue(), (Integer) textFieldHeight.getValue(), false, btnInnerColor.getBackground(), btnOutlineColor.getBackground());
	}

	public DglRectangle() {
		setModal(true);
		setTitle("Rectangle informations");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[50%][50%,grow]", "[24px][14px][14px][14px][14px][14px][14px]"));
		{
			JLabel lblRectangle = new JLabel("Rectangle");
			lblRectangle.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblRectangle, "cell 0 0");
		}
		{
			JLabel lblX = new JLabel("X coordinate of upper left point ");
			contentPanel.add(lblX, "cell 0 1,alignx center,aligny center");
		}
		{
			textFieldX = new JSpinner();
			contentPanel.add(textFieldX, "cell 1 1,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldX.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblY = new JLabel("Y coordinate of upper left point ");
			contentPanel.add(lblY, "cell 0 2,alignx center,aligny center");
		}
		{
			textFieldY = new JSpinner();
			contentPanel.add(textFieldY, "cell 1 2,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldY.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblWidth = new JLabel("Width");
			contentPanel.add(lblWidth, "cell 0 3,alignx center,aligny center");
		}
		{
			textFieldWidth = new JSpinner();
			contentPanel.add(textFieldWidth, "cell 1 3,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldWidth.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblHeight = new JLabel("Height");
			contentPanel.add(lblHeight, "cell 0 4,alignx center,aligny center");
		}
		{
			textFieldHeight = new JSpinner();
			contentPanel.add(textFieldHeight, "cell 1 4,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldHeight.getEditor();
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
			contentPanel.add(btnInnerColor, "cell 0 5,alignx center,aligny center");
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
			contentPanel.add(btnOutlineColor, "cell 0 6,alignx center,aligny center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (textFieldWidth.getValue() != null && textFieldHeight.getValue() != null && textFieldX.getValue() != null && textFieldY.getValue() != null) {
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
