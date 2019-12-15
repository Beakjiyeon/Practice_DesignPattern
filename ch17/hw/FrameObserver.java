package ch17.hw;

import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameObserver extends Frame implements Observer, ActionListener {
	private GraphText textGraph = new GraphText(60);
	// ������ : GraphText�� ������ ������  text field�� �����
	private GraphNamePrint textGraph2=new GraphNamePrint(60);

	private GraphCanvas canvasGraph = new GraphCanvas();

	private Button buttonClose = new Button("Close");

	public FrameObserver() {
		super("FrameObserver");
		// BorderLayout�� GUI ������Ʈ�� ��������,�߾� �� �ϳ��� ��ġ�Ѵ�.
		setLayout(new BorderLayout());

		setBackground(Color.lightGray);
		textGraph.setEditable(false);
		canvasGraph.setSize(500, 500);

		// ������ : textGraph2�� textGraph �Ʒ��� ��ġ ��Ű�� ���� �г��� ���
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2,1));
		p.add(textGraph);
		p.add(textGraph2);
		
		// �� GUI ������Ʈ�� ��, �߾�, ���� �ܴ�.
		// ������ : �ǳ��� �������� ���ʿ� ����.
		add(p,BorderLayout.NORTH);
		add(canvasGraph, BorderLayout.CENTER);
		add(buttonClose, BorderLayout.SOUTH);
		// Close ��ư�� ������ ���� ActionListener�� ����Ѵ�.
		buttonClose.addActionListener(this);
		
		pack(); // ������ ũ�⸦ �����Ѵ�.
		show(); // ȭ�鿡 ���δ�.
	}

	// Close ��ư�� ������ �� ȣ��ȴ�.
	public void actionPerformed(ActionEvent e) {
		System.out.println("" + e);
		System.exit(0);
	}

	public void update(NumberGenerator generator) {
		textGraph.update(generator);
		textGraph2.update(generator);
		canvasGraph.update(generator);
	}
}

class GraphText extends TextField implements Observer {
	public GraphText(int columns) {
		super(columns);
	}

	public void update(NumberGenerator generator) {
		int number = generator.getNumber();
		String text = number + ":";
		for (int i = 0; i < number; i++) {
			text += '*';
		}
		setText(text); // �ؽ�Ʈ �ʵ忡 text�� ����Ѵ�.
	}
}
class GraphNamePrint extends TextField implements Observer{
	
	// ������ : �ؽ�Ʈ �ʵ� ������super�� ȣ��
	public GraphNamePrint(int columns) {
		// TODO Auto-generated constructor stub
		super(columns);
	}
	String text;
	
	// ������ : �ؽ�Ʈ �ʵ忡 ���� ����ŭ �̸� ���
	@Override
	public void update(NumberGenerator generator) {
		int num=generator.getNumber();
		text=num+": ";
		for(int i=0;i<num;i++)
			text+="������ ";
		setText(text);
	}
	
	

}
// Canvas: �׸� �׸� �� �ִ� �� �簢���� ��Ÿ���� Ŭ����
class GraphCanvas extends Canvas implements Observer {
	private int number;

	public void update(NumberGenerator generator) {
		number = generator.getNumber();
		repaint();  // paint() �޼ҵ带 �ڵ����� ȣ���Ѵ�.

	}

	// Graphics: �׸� �׸��� ���� ���� ���� �޼ҵ�� 
	// �׸��� ���� ����(����, ��Ʈ ��)�� ������ �ִ� Ŭ����
	// paint() �޼ҵ�� Graphics ��ü�� ���ڷ� ���޹޴´�.
	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		// ���� ���� ������� �����Ѵ�.
		g.setColor(Color.white);
		// 0������ 360���� ��ȣ�� �׸���.
		g.fillArc(0, 0, width, height, 0, 360);
        // ���� ���� ���������� �����Ѵ�.
		g.setColor(Color.red);
		// 90������ �ð�������� ��ȣ�� �׸���.
		g.fillArc(0, 0, width, height, 90, -number * 360 / 50);
	}
}