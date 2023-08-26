package oldProject.Sort;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import oldProject.Geometry.Point;
import oldProject.Geometry.Rectangle;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RectangleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	public boolean isOk;

	public static void main(String[] args) {
		try {
			RectangleDialog dialog = new RectangleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Rectangle GetData() {
		Point point = new Point(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText()));
		Rectangle rec = new Rectangle(point, Integer.parseInt(textFieldWidth.getText()), Integer.parseInt(textFieldHeight.getText()));
		return rec;
	}
	
	public void SetData(int x, int y, int width, int height) {
		textFieldX.setText(Integer.toString(x));
		textFieldY.setText(Integer.toString(y));
		textFieldWidth.setText(Integer.toString(width));
		textFieldHeight.setText(Integer.toString(height));
		
		textFieldX.setEditable(false);
		textFieldY.setEditable(false);
		textFieldWidth.setEditable(false);
		textFieldHeight.setEditable(false);
	}

	public RectangleDialog() {
		setTitle("Adding rectangle");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[50%][50%,grow]", "[24px][14px][14px][14px][14px]"));
		{
			JLabel lblHeader = new JLabel("Rectangle");
			lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblHeader, "cell 0 0");
		}
		{
			JLabel lblX = new JLabel("X coordinate");
			contentPanel.add(lblX, "flowx,cell 0 1,alignx center,aligny center");
		}
		{
			textFieldX = new JTextField();
			contentPanel.add(textFieldX, "cell 1 1,growx,aligny center");
			textFieldX.setColumns(1);
		}
		{
			JLabel lblY = new JLabel("Y coordinate");
			contentPanel.add(lblY, "flowx,cell 0 2,alignx center");
		}
		{
			textFieldY = new JTextField();
			contentPanel.add(textFieldY, "cell 1 2,growx");
			textFieldY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width");
			contentPanel.add(lblWidth, "cell 0 3,alignx center");
		}
		{
			textFieldWidth = new JTextField();
			contentPanel.add(textFieldWidth, "cell 1 3,growx");
			textFieldWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height");
			contentPanel.add(lblHeight, "cell 0 4,alignx center");
		}
		{
			textFieldHeight = new JTextField();
			contentPanel.add(textFieldHeight, "cell 1 4,growx");
			textFieldHeight.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						isOk = true;
						setVisible(false);
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

}
