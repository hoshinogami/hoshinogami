package com.game.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;
public class GameJFrame extends JFrame implements KeyListener,ActionListener{
	    int[][] data=new int[4][4];
	    int x,y;
	    int[][]win= {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0 }};
	    int step=0;
	  //创建选项下的条目对象
   	 JMenuItem replayitem=new JMenuItem("重新游戏");
   	 JMenuItem reloginitem=new JMenuItem("重新登录");
   	 JMenuItem closeitem=new JMenuItem("关闭游戏");
   	 
   	 JMenuItem accountitem=new JMenuItem("公众号");
   	 
	         //生成界面
            public GameJFrame() {
            	//宽高
            	this.setSize(603,680);
            	//设置界面标题
            	this.setTitle("拼图单机版"); 
            	//设置标题置顶 
            	this.setAlwaysOnTop(true);
            	//设置界面居中
            	this.setLocationRelativeTo(null);
            	//设置关闭
            	this.setDefaultCloseOperation(3);
            	//取消默认居中
            	this.setLayout(null);
            	//摁键监听
            	this.addKeyListener(this);
            	//设置菜单
            	
            	initJMenuBar();
            	//初始化数据
            	 initData();
            	//图片
            	initImage();
            	//显示界面
          	   this.setVisible(true);
            }  
            
private void initJMenuBar() {
            	//初始化菜单
            	//创建整个菜单
            	JMenuBar JMenuBar=new JMenuBar();
            	//创建功能和关于我们
            	JMenu funtionJMenu=new JMenu("功能");
            	 JMenu about=new JMenu("关于我们");
            	 
            	 //添加到相关选项中
            	 funtionJMenu.add(replayitem);
            	 funtionJMenu.add(reloginitem);
            	 funtionJMenu.add(closeitem);
            	 
            	 about.add(accountitem);
            	 
            	 //给条目绑定事件
            	 replayitem.addActionListener(this);
            	 reloginitem.addActionListener(this);
            	 closeitem.addActionListener(this);
            	 accountitem.addActionListener(this);
            	 JMenuBar.add(funtionJMenu);
            	 JMenuBar.add(about);
            	 this.setJMenuBar(JMenuBar);
            }

private void initImage() {
	//清空图片
	this.getContentPane().removeAll();
	
	if(victory()) {
		JLabel winJLabel=new JLabel(new ImageIcon("src\\image\\win.png"));
		winJLabel.setBounds(203, 283, 197, 73);
		this.getContentPane().add(winJLabel);
		
	}
	JLabel stepCount=new JLabel("步数："+step);
	stepCount.setBounds(50,30,100,20);
	this.getContentPane().add(stepCount);
    for(int j=0;j<4;j++) {
	for(int i=0;i<4;i++) {
	//获取图片
	ImageIcon icon1=new ImageIcon("src\\image\\animal\\animal3\\"+data[j][i]+".jpg");
	JLabel jLabel1=new JLabel(icon1);
	//图片位置
	jLabel1.setBounds(105*i+83, 105*j+134, 105, 105);
	//给图片添加边框
	jLabel1.setBorder(new BevelBorder(1));
	
    //添加到屏幕中
	this.getContentPane().add(jLabel1);
	
}
    }
  //添加背景图片
  		ImageIcon bg=new ImageIcon("src\\image\\background.png");
  		JLabel background=new JLabel(bg);
  		background.setBounds(45, 70, 500, 500);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
}
private void initData() {
	int []tempARR=new int[16];
	for(int i=0;i<16;i++) {
		tempARR[i]=i;
		}
	Random r=new Random();
	for(int i=0;i<16;i++) {
		int index=r.nextInt(tempARR.length);
		int tem=tempARR[i];
		tempARR[i]=tempARR[index];
		tempARR[index]=tem;
	}
	for(int i=0;i<tempARR.length;i++) {
		if(tempARR[i]==0) {
			x=i/4;
			y=i%4;
		}
		data[i/4][i%4]=tempARR[i];
	}
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	int code=e.getKeyCode();
	if(code==65) {
		//先清空图
		this.getContentPane().removeAll();
		JLabel all=new JLabel(new ImageIcon("src\\\\image\\\\animal\\\\animal3\\all.jpg"));
		all.setBounds(83, 134, 420, 420);
		this.getContentPane().add(all);
		ImageIcon bg=new ImageIcon("src\\image\\background.png");
  		JLabel background=new JLabel(bg);
  		background.setBounds(45, 70, 500, 500);
		this.getContentPane().add(background);
		this.getContentPane().repaint();
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	//对上下左右进行判断
	if(victory()) {
		return;
	}
	int code=e.getKeyCode();
	if(code==65) {
		initImage();
	}
	if(code==37) {
		if(y==3) {
			return;
		}
		data[x][y]=data[x][y+1];
		data[x][y+1]=0;
	     y++;
	     initImage();
	     step++;
	}else if(code==38) {
		if(x==3) {
			return;
		}
		data[x][y]=data[x+1][y];
		data[x+1][y]=0;
	     x++;
	     initImage();
	     step++;
	}else if(code==39) {
		if(y==0) {
			return;
		}
		data[x][y]=data[x][y-1];
		data[x][y-1]=0;
	     y--;
	     initImage();
	     step++;
	}else if(code==40) {
		if(x==0) {
			return;
		}
		data[x][y]=data[x-1][y];
		data[x-1][y]=0;
	     x--;
	     initImage();
	     step++;
	}
}
//判断是否胜利
public boolean victory() {
	for(int i=0;i<data.length;i++) {
		for(int j=0;j<data[i].length;j++) {
		if(	data[i][j]!=win[i][j]) {
			return false;
		}
		}
	}
	return true;
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object obj=e.getSource();
	if(obj==replayitem) {
		step=0;
		initData();
		initImage();
		
	}
	else if(obj==reloginitem) {
		//关闭当前界面
		this.setVisible(false);
		//打开登录界面
		new LoginJFrame();
		
	}
	else if(obj==closeitem) {
		System.exit(0);
	}
	else if(obj==accountitem) {
		//创建一个弹窗
		JDialog jDialog= new JDialog();
		JLabel jLabel=new JLabel("未完成");
		jLabel.setBounds(0, 0, 258, 258);
		jDialog.getContentPane().add(jLabel);
		jDialog.setSize(344,344);
		jDialog.setAlwaysOnTop(true);
		jDialog.setLocationRelativeTo(null);
		jDialog.setModal(true);
		jDialog.setVisible(true);
	}
	}
}
