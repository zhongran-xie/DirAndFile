/**
 * 
 */
package com.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**   
 * @ClassName:  Gui   
 * @Description:TODO  窗口控件设置
 * @author: xzr 
 * @date:   2019年5月8日 
 *     
 */
public class Gui extends JFrame{
    /**   
     * @Fields serialVersionUID : TODO   
     */  
    private static final long serialVersionUID = 1L;
    // 创建文本
    public final JLabel jLabel1 = new JLabel("原目录");
    public final JLabel jLabel2 = new JLabel("新目录");
    public final JLabel jLabel3 = new JLabel("过滤目录");
    public final JLabel jLabel4 = new JLabel("过滤文件");
    // 创建文本框，指定可见列数为8列
    public final JTextField textField1 = new JTextField(8);
    public final JTextField textField2 = new JTextField(8);
    public final JTextField textField3 = new JTextField(8);
    public final JTextField textField4 = new JTextField(8);
    // 按钮
    public final JButton btn = new JButton("开始复制");

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    
    public Gui() {
	JFrame jf = new JFrame("复制文件"); // 实例化一个JFrame对象
	JLabel j=new JLabel();
	jf.setLayout(null);
	Container panel=getContentPane();
	
//	JPanel panel = new JPanel();

	// 设置文本字体
	jLabel1.setFont(new Font(null, Font.PLAIN, 20));
	jLabel2.setFont(new Font(null, Font.PLAIN, 20));
	jLabel3.setFont(new Font(null, Font.PLAIN, 20));
	jLabel4.setFont(new Font(null, Font.PLAIN, 20));
	//设置位置
	jLabel1.setBounds(50, 20, 100, 30);
	jLabel2.setBounds(50, 60, 100, 30);
	jLabel3.setBounds(50, 100, 100, 30);
	jLabel4.setBounds(50, 140, 100, 30);
	
	// 设置文本框字体
	textField1.setFont(new Font(null, Font.PLAIN, 20));
	textField2.setFont(new Font(null, Font.PLAIN, 20));
	textField3.setFont(new Font(null, Font.PLAIN, 20));
	textField4.setFont(new Font(null, Font.PLAIN, 20));
	//设置位置
	textField1.setBounds(150, 20, 150, 30);
	textField2.setBounds(150, 60, 150, 30);
	textField3.setBounds(150, 100, 150, 30);
	textField4.setBounds(150, 140, 150, 30);
	// 设置按字体
	btn.setFont(new Font(null, Font.PLAIN, 20));
	//设置按钮位置
	btn.setBounds((350-150)/2, 180, 150, 30);
//	btn.setSize(150, 30);     // 绝对布局的宽高设置，使用 setSize(...)
//	btn.setLocation((350-btn.getWidth())/2, 180);  // 坐标指的是相对于父容器左上角的坐标
//	button.setBounds((this.getWidth()-button.getWidth()-5)/2,(this.getHeight()-28-button.getHeight())/2,button.getWidth(),button.getHeight());
	panel.add(jLabel1);
	panel.add(textField1);
	panel.add(jLabel2);
	panel.add(textField2);
	panel.add(jLabel3);
	panel.add(textField3);
	panel.add(jLabel4);
	panel.add(textField4);
	panel.add(btn);
	
	j.setHorizontalAlignment(SwingConstants.CENTER);
	j.setOpaque(true);
	panel.add(j);
	
	setSize(350, 300); // 设置窗体大小
	setLocationRelativeTo(null);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置窗体关闭方式
//	setContentPane(panel);
	setVisible(true); // 设置窗体可视
	
	setResizable(false);

    }
}
