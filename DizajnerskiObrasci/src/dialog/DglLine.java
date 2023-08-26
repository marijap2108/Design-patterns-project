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

import model.shape.Line;
import model.shape.Point;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DglLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner textFieldX1;
	private JSpinner textFieldY1;
	private JSpinner textFieldX2;
	private JSpinner textFieldY2;
	private JButton btnColor;
	public boolean isOk;

	public static void main(String[] args) {
		try {
			DglLine dialog = new DglLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SetData(int x1, int y1, int x2, int y2) {
		textFieldX1.setValue(x1);
		textFieldY1.setValue(y1);
		textFieldX2.setValue(x2);
		textFieldY2.setValue(y2);
		
		textFieldX1.setEnabled(false);
		textFieldY1.setEnabled(false);
		textFieldX2.setEnabled(false);
		textFieldY2.setEnabled(false);
	}
	
	public void SetData(Line line) {
		textFieldX1.setValue(line.getStartPoint().getX());
		textFieldY1.setValue(line.getStartPoint().getY());
		textFieldX2.setValue(line.getEndPoint().getX());
		textFieldY2.setValue(line.getEndPoint().getY());
		btnColor.setBackground(line.getColor());
	}

	public Line getLine() {
		return new Line(new Point((Integer) textFieldX1.getValue(),(Integer) textFieldY1.getValue()), new Point((Integer) textFieldX2.getValue(), (Integer) textFieldY2.getValue()), false, btnColor.getBackground());
	}
	
	public DglLine() {
		setTitle("Line informations");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[50%][50%,grow]", "[24px][14px][14px][14px][14px][14px]"));
		{
			JLabel lblLine = new JLabel("Line");
			lblLine.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblLine, "cell 0 0");
		}
		{
			JLabel lblX1 = new JLabel("X coordinate of start point");
			contentPanel.add(lblX1, "cell 0 1,alignx center,aligny center");
		}
		{
			textFieldX1 = new JSpinner();
			contentPanel.add(textFieldX1, "cell 1 1,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldX1.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblY1 = new JLabel("Y coordinate of start point");
			contentPanel.add(lblY1, "cell 0 2,alignx center,aligny center");
		}
		{
			textFieldY1 = new JSpinner();
			contentPanel.add(textFieldY1, "cell 1 2,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldY1.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblX2 = new JLabel("X coordinate of end point");
			contentPanel.add(lblX2, "cell 0 3,alignx center,aligny center");
		}
		{
			textFieldX2 = new JSpinner();
			contentPanel.add(textFieldX2, "cell 1 3,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldX2.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblY2 = new JLabel("Y coordinate of end point");
			contentPanel.add(lblY2, "cell 0 4,alignx center,aligny center");
		}
		{
			textFieldY2 = new JSpinner();
			contentPanel.add(textFieldY2, "cell 1 4,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldY2.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			btnColor = new JButton("Color");
			btnColor.setOpaque(true);
			btnColor.setBorderPainted(false);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color Color = JColorChooser.showDialog(null, "Choose color", btnColor.getBackground());
					if (Color != null)
						btnColor.setBackground(Color);
				}
			});
			contentPanel.add(btnColor, "cell 0 5,alignx center,aligny center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (textFieldX1.getValue() != null && textFieldY1.getValue() != null && textFieldX2.getValue() != null && textFieldY2.getValue() != null) {
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

	public void SetData(int x, int y, int x2, int y2, Color innerColor) {
		SetData(x, y, x2, y2);
		btnColor.setBackground(innerColor);
		btnColor.setEnabled(false);
	}


}
