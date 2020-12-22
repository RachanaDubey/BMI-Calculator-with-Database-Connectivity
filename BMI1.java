import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class BMI1 extends JFrame implements ActionListener{
ResultSet rs;
JTextField text1,text2,text3,text4,text5;
JLabel label1,label2,label3,label4,label5,label6;
JPanel mainpanel,panel,panel1;
JButton button1,button2,button3,button4;
BMI1() {
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
Date date = new Date();
JFrame f=new JFrame(dateFormat.format(date)+" Mini Project");
text1=new JTextField(15);
text2=new JTextField(15);
text3=new JTextField(15);
text4=new JTextField(15);
text5=new JTextField(15);
label1=new JLabel("Name");
label2=new JLabel("Weight (in kg)");
label3=new JLabel("Height (in metre)");
label4=new JLabel("Your BMI");
label5=new JLabel("Remarks");
label6=new JLabel("BMI CALCULATOR");
label6.setFont(new Font("Times New Roman", Font.BOLD, 26));
button1=new JButton("Calculate");
button2=new JButton("Save");
button3=new JButton("Prev");
button4=new JButton("Next");
mainpanel=new JPanel(new FlowLayout());
panel1=new JPanel(new FlowLayout());
panel1.add(label6);
panel1.validate();
panel=new JPanel(new GridLayout(9,2));
panel.add(label1);
panel.add(text1);
panel.add(label2);
panel.add(text2);
panel.add(label3);
panel.add(text3);
panel.add(label4);
panel.add(text4);
panel.add(label5);
panel.add(text5);
panel.add(button1);
panel.add(button2);
panel.add(button3);
panel.add(button4);
button1.addActionListener(this);
button2.addActionListener(this);
button3.addActionListener(this);
button4.addActionListener(this);
mainpanel.add(panel1);
mainpanel.add(panel);
f.add(mainpanel);
f.setSize(400,300);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setVisible(true);
}
public void actionPerformed(ActionEvent ae)
{
String action=ae.getActionCommand();
if(action.equals("Calculate"))
calculateoperation();
else if(action.equals("Save"))
saveoperation();
else if(action.equals("Prev"))
prevnavigation();
else if(action.equals("Next"))
nextnavigation();
}
void calculateoperation()
{
int n1=Integer.parseInt(text2.getText());
float n2=Float.parseFloat(text3.getText());
float q=n2*n2;
float s=((n1*100)/q);
text4.setText(Float.toString(s));
float bmi=Float.parseFloat(text4.getText());
if(bmi<18.5)
text5.setText("Underweight");
else if(bmi>18.5 && bmi<24.9)
text5.setText("Healthy");
else if(bmi>20.0 && bmi<29.9)
text5.setText("Overweight");
else
text5.setText("Obese");
}
void saveoperation()
{
String val1=text1.getText();
String val2=text2.getText();
String val3=text3.getText();
String val4=text4.getText();
String val5=text5.getText();
String url="jdbc:odbc:sample";
try{
Class.forName("sun.jdbc.odbc.JdbcOdbc");
Connection con=DriverManager.getConnection(url);
Statement st = con.createStatement();
ResultSet rs=st.executeQuery("Select * from bmi where username='"+val1+"'");
String usern="";
if(rs.next()){
usern=rs.getString("username");
JOptionPane.showMessageDialog(null,"Invalid UserName");
}
else {
int k=st.executeUpdate("insert into bmi(username,weight,height,bmival,remarks) values('"+val1+"','"+val2+"','"+val3+"','"+val4+"','"+val5+"')");
JOptionPane.showMessageDialog(null,"Data is successfully saved");
}
}
catch(Exception e){}
}
void prevnavigation()
{
String url="jdbc:odbc:sample";
try{
if(rs == null)
{
Class.forName("sun.jdbc.odbc.JdbcOdbc");
Connection con=DriverManager.getConnection(url);
String sql = "SELECT * FROM bmi";
Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
ResultSet.CONCUR_UPDATABLE);
rs = st.executeQuery(sql);
}
if(rs.previous())
{populateValue();}
}catch(Exception e)
{
JOptionPane.showMessageDialog(null, e.getMessage(),"Error",
JOptionPane.ERROR_MESSAGE);
}
}
void nextnavigation()
{
String url="jdbc:odbc:sample";
try{
if(rs == null)
{//Load Jdbc Odbc Driver
Class.forName("sun.jdbc.odbc.JdbcOdbc");
Connection con=DriverManager.getConnection(url);
String sql = "SELECT * FROM bmi";
Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
ResultSet.CONCUR_UPDATABLE);
rs = st.executeQuery(sql);
}
if(rs.next())
{populateValue();
}
}catch(Exception e)
{
JOptionPane.showMessageDialog(null, e.getMessage(),"Error",
JOptionPane.ERROR_MESSAGE);
}
}
void populateValue()
{
Try  {
String name = rs.getString("username");
String wei = rs.getString("weight");
String hei = rs.getString("height");
String bmi = rs.getString("bmival");
String rmk= rs.getString("remarks");
text1.setText(name);
text2.setText(wei);
text3.setText(hei);
text4.setText(bmi);
text5.setText(rmk);  }
catch(SQLException e){}
}
public static void main(String ar[])
{
new BMI1();}}
