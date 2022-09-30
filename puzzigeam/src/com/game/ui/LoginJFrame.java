package com.game.ui;

import javax.swing.*;
public class LoginJFrame extends JFrame{
           public LoginJFrame() {
        	  this.setSize(480, 470);
        	  this.setTitle("拼图单机版"); 
          	//设置标题置顶 
          	this.setAlwaysOnTop(true);
          	//设置界面居中
          	this.setLocationRelativeTo(null);
          	//设置关闭
          	this.setDefaultCloseOperation(3);
          	//显示界面
        	   this.setVisible(true);
           }
}
