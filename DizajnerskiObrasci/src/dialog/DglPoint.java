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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DglPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPoint = new JPanel();
	private JSpinner textFieldX;
	private JSpinner textFieldY;
	private JButton btnColor;
	public boolean isOk;

	public static void main(String[] args) {
		try {
			DglPoint dialog = new DglPoint();
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
	
	public void SetData(Point point) {
		textFieldX.setValue(point.getX());
		textFieldY.setValue(point.getY());
		btnColor.setBackground(point.getColor());	
	}
	
	public Point getPoint() {
		return (new Point((Integer) textFieldX.getValue(), (Integer) textFieldY.getValue(), false, btnColor.getBackground()));
	}

	public DglPoint() {
		setTitle("Point informations");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPoint.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPoint, BorderLayout.CENTER);
		contentPoint.setLayout(new MigLayout("", "[50%][50%,grow]", "[24px][14px][14px][14px]"));
		{
			JLabel lblPoint = new JLabel("Point");
			lblPoint.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPoint.add(lblPoint, "cell 0 0");
		}
		{
			JLabel lblX = new JLabel("X coordinate of point");
			contentPoint.add(lblX, "flowx,cell 0 1,alignx center,aligny center");
		}
		{
			textFieldX = new JSpinner();
			contentPoint.add(textFieldX, "cell 1 1,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldX.getEditor();
			DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
		}
		{
			JLabel lblY = new JLabel("Y coordinate of point");
			contentPoint.add(lblY, "flowx,cell 0 2,alignx center,aligny center");
		}
		{
			textFieldY = new JSpinner();
			contentPoint.add(textFieldY, "cell 1 2,growx");
			JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) textFieldY.getEditor();
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
			contentPoint.add(btnColor, "cell 0 3,alignx center,aligny center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (textFieldX.getValue() != null && textFieldY.getValue() != null) {
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

	public void SetData(int x, int y, Color innerColor) {
		SetData(x, y);
		btnColor.setBackground(innerColor);
		btnColor.setEnabled(false);
	}
}
