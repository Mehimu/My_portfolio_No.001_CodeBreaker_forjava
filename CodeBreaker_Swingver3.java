import java.util.* ;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class CodeBreaker_Swingver3 implements ActionListener{
    JFrame frame;
    JLabel title;
    JLabel textAria;
    JLabel subText;
    JLabel tentative1,tentative2,tentative3,tentative4; //解答保持ラベル
    JLabel tent[] = {tentative1,tentative2,tentative3,tentative4};
    String tentText[] = {"","","",""};
    static JButton number1,number2,number3,number4,number5,number6,number7,number8,number9,number0;
    JButton enter;
    JButton reset;
    JButton giveUp;

    JTable table;
    DefaultTableModel tableModel;
    int tRowCount = 0;
    int tColumnCount = 0; 

    static int answerlength = 4;
    static int windthOfRandom = 9; //固定
    int[] answer;
    int[] input;

    int hit = 0;
    int blow = 0;
    int count = 0;

    static Container pane;
    String snull = "";

    public static void main(String[] args){

        CodeBreaker_Swingver3 codeBreaker3 = new CodeBreaker_Swingver3();
        codeBreaker3.MakeAnswers();
        codeBreaker3.GameEngine();
        
    }
    public void GameEngine(){
        frame = new JFrame("Hit&blow Swingバージョン3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        pane = frame.getContentPane();
        var canvas1 = new JPanel(); //ゲーム画面
        canvas1.setLayout(null); 
        //canvas1.setBackground(new Color(34,139,34));

        String[] tHader = {"Number","Hit&Blow"}; 
        tableModel = new DefaultTableModel(tHader, 50);
        table = new JTable(tableModel);
        table.setEnabled(false);
        table.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        table.setRowHeight(23);
        JTableHeader jh = table.getTableHeader();
        jh.setFont(new Font(jh.getFont().getFamily(),Font.PLAIN,15));
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumn tableCol1 = table.getColumnModel().getColumn(0);
        TableColumn tableCol2 = table.getColumnModel().getColumn(1);
        tableCol1.setCellRenderer(tableCellRenderer);
        tableCol2.setCellRenderer(tableCellRenderer);
        JScrollPane sp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(120,15,220,280);
        canvas1.add(sp);

        textAria = new JLabel("");
        textAria.setBounds(360,15,220,280);
        textAria.setBorder(new LineBorder(new Color(0,191,255),2,false));
        String ruleText = "<html>4つの数字を<br >当ててください<br >数字は重複しません<br >入力した数字の位置と<br >数字が当たってたらHit<br >数字だけあってたらBlowとカウントします。<br >隠された4つの数字とその場所を当てたら終了です</html>";
        textAria.setText(ruleText);
        textAria.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        canvas1.add(textAria);
        
        subText = new JLabel("値を入力してください");
        subText.setBounds(20,305,660,50);
        subText.setFont(new Font(Font.DIALOG, Font.PLAIN, 40));
        subText.setHorizontalAlignment(JLabel.CENTER);
        canvas1.add(subText);

        //for文でJlabelの追加
        int tentX = 130; int tentY = 365;
        for(int j = 0; j < 4; j++){
            tent[j] = new JLabel(tentText[j]);
            tent[j].setFont(new Font("Arial", Font.PLAIN, 40));
            tent[j].setHorizontalAlignment(JLabel.CENTER);
            tent[j].setBounds(tentX,tentY,100,100);
            tent[j].setBorder(new LineBorder(new Color(0,191,255),2,false));
            tent[j].setBackground(new Color(224,255,255));
            canvas1.add(tent[j]);
            tentX += 110;
        }

        JButton enter = new JButton("OK!");
        enter.setBounds(570,375,90,80);
        enter.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        enter.addActionListener(this);
        canvas1.add(enter);

        reset = new JButton("再始");
        reset.setBounds(40,365,85,45);
        reset.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        reset.addActionListener(this);
        canvas1.add(reset);

        giveUp = new JButton("解答");
        giveUp.setBounds(40,420,85,45);
        giveUp.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        giveUp.addActionListener(this);
        canvas1.add(giveUp);

        number1 = new JButton("1");
        number1.setFont(new Font("Arial", Font.PLAIN, 40));
        number1.setBounds(40,480,120,85);
        number1.addActionListener(this);
        canvas1.add(number1);

        number2 = new JButton("2");
        number2.setFont(new Font("Arial", Font.PLAIN, 40));
        number2.setBounds(165,480,120,85);
        number2.addActionListener(this);
        canvas1.add(number2);

        number3 = new JButton("3");
        number3.setFont(new Font("Arial", Font.PLAIN, 40));
        number3.setBounds(290,480,120,85);
        number3.addActionListener(this);
        canvas1.add(number3);

        number4 = new JButton("4");
        number4.setFont(new Font("Arial", Font.PLAIN, 40));
        number4.setBounds(415,480,120,85);
        number4.addActionListener(this);
        canvas1.add(number4);

        number5 = new JButton("5");
        number5.setFont(new Font("Arial", Font.PLAIN, 40));
        number5.setBounds(540,480,120,85);
        number5.addActionListener(this);
        canvas1.add(number5);

        number6 = new JButton("6");
        number6.setFont(new Font("Arial", Font.PLAIN, 40));
        number6.setBounds(40,570,120,85);
        number6.addActionListener(this);
        canvas1.add(number6);

        number7 = new JButton("7");
        number7.setFont(new Font("Arial", Font.PLAIN, 40));
        number7.setBounds(165,570,120,85);
        number7.addActionListener(this);
        canvas1.add(number7);

        number8 = new JButton("8");
        number8.setFont(new Font("Arial", Font.PLAIN, 40));
        number8.setBounds(290,570,120,85);
        number8.addActionListener(this);
        canvas1.add(number8);

        number9 = new JButton("9");
        number9.setFont(new Font("Arial", Font.PLAIN, 40));
        number9.setBounds(415,570,120,85);
        number9.addActionListener(this);
        canvas1.add(number9);

        number0 = new JButton("0");
        number0.setFont(new Font("Arial", Font.PLAIN, 40));
        number0.setBounds(540,570,120,85);
        number0.addActionListener(this);
        canvas1.add(number0);

        pane.add(canvas1);
        frame.setVisible(true); 
        //最後に記述することで起動した時に表示されるように
    
    }
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "OK!":
                //入力値と回答を判別するメソッド記述
                hit = 0; blow = 0;
                String t1 = tent[0].getText();
                String t2 = tent[1].getText();
                String t3 = tent[2].getText();
                String t4 = tent[3].getText();
                if (!(t1.equals(snull)) && !(t2.equals(snull)) && !(t3.equals(snull)) && !(t4.equals(snull))){
                    String tent = t1 + t2 + t3 + t4;
                    table.setValueAt(tent,tRowCount,tColumnCount);
                    tColumnCount += 1;
                    for(int i = 0; i < answerlength; i++){
                        for(int j = 0; j < answerlength; j++){
                            if(i == j && input[i] == answer[j]){
                                hit++;
                            }
                            else if(input[i] == answer[j]){
                                blow++;
                            }
                        }
                    }
                    table.setValueAt(hit + "&" + blow, tRowCount, tColumnCount);
                    tRowCount += 1; tColumnCount -= 1;
                    if(hit == 4){
                        subText.setText("大正解！");
                        stateButtonAction("answer");
                    }
                    else if(hit + blow == 3){
                        subText.setText("もう少し");
                    }
                    else if(hit == 0 && blow == 0){
                        subText.setText("ラッキー");
                    }
                    else if(blow == 4){
                        subText.setText("数字は正解！！");
                    }
                    else{
                        subText.setText("いいね！");
                    }
                    
                }
                else{
                    subText.setText("値が足りません");
                }
                for(int p = 0; p < answerlength; p++){
                    input[p] = -1;
                    tent[p].setText("");
                }
                break;
            case "再始":
                MakeAnswers();
                subText.setText("解答が変更されました");
                while (0 <= tRowCount) {
                    tableModel.removeRow(tRowCount);
                    tRowCount -= 1;
                }
                tRowCount = 0; //消さないで！
                stateButtonAction("restart");
                break;
            case "解答":
                String ans = Arrays.toString(answer);
                subText.setText(ans);
                stateButtonAction("answer");
                break;
            case "1":
                clickNumber("1");
                break;
            case "2":
                clickNumber("2");
                break;
            case "3":
                clickNumber("3");
                break;
            case "4":
                clickNumber("4");
                break;
            case "5":
                clickNumber("5");
                break;
            case "6":
                clickNumber("6");
                break;
            case "7":
                clickNumber("7");
                break;
            case "8":
                clickNumber("8");
                break;
            case "9":
                clickNumber("9");
                break;
            case "0":
                clickNumber("0");
                break;
        }
    }
    void stateButtonAction(String state){
        if (state.equals("answer")) {
            number1.setEnabled(false);
            number2.setEnabled(false);
            number3.setEnabled(false);
            number4.setEnabled(false);
            number5.setEnabled(false);
            number6.setEnabled(false);
            number7.setEnabled(false);
            number8.setEnabled(false);
            number9.setEnabled(false);
            number0.setEnabled(false);
        }
        else if(state.equals("restart")){
            number1.setEnabled(true);
            number2.setEnabled(true);
            number3.setEnabled(true);
            number4.setEnabled(true);
            number5.setEnabled(true);
            number6.setEnabled(true);
            number7.setEnabled(true);
            number8.setEnabled(true);
            number9.setEnabled(true);
            number0.setEnabled(true);
        }
    }
    void clickNumber(String str){
        /*tent[]それぞれをカウントアップさせながら
         *値が入っていない場合、挿入*/
        int num = Integer.parseInt(str);
        for(int w = 0; w < answerlength; w++){
            if(input[w] == -1){
                if(w > 0){
                    boolean flag = false;
                    for(int k = w; k >= 0;k--){
                        if(input[w - k] == num){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        input[w] = num;
                        tent[w].setText(str);
                        break;
                    }
                }
                else{
                    input[w] = num;
                    tent[w].setText(str);
                }
                break;
            }
        }
    }
    void MakeAnswers(){ //解答作成
        answer = new int[answerlength];
        input = new int[answerlength];

        if(answer.length != answerlength){
        answer = new int[answerlength];
        input = new int[answerlength];
        }
        for(int i = 0; i < answerlength; i++){
            input[i] = -1;
            boolean flag = false;
            answer[i] = (int)(Math.random() * windthOfRandom + 1);
            do{
                flag = false;
                for(int q = i - 1; q >= 0; q--){
                    if(answer[i] == answer[q]){
                        flag = true;
                        answer[i] = (int)(Math.random() * windthOfRandom + 1);
                    }
                }
            }while(flag == true);
        }
    }
}
