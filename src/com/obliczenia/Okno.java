package com.obliczenia;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class Okno extends JFrame {

    //stan kalkulatora
    private enum Stan {pocz, arg1, operator, arg2};
    private Stan stan = Stan.pocz;
    private int podstawa = 10;
    private String działanie = "";
    private BigInteger operand = null;

    private JTextField arg = new JTextField();
    private JTextField wyn = new JTextField();
    {
        arg.setFocusable(false);
        arg.setEditable(false);
        wyn.setEditable(false);
    }

    // zdarzenia od przycisków z cyframi - dodanie cyfry do argumentu
    ActionListener sluchaczCyfr = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            JButton b = (JButton) ev.getSource();
//            String c = b.getText();
//            arg.setText(arg.getText() + c);
//        }
            JButton[] przycisk = new JButton[16];
            przycisk[0] = c0;
            przycisk[1] = c1;
            przycisk[2] = c2;
            przycisk[3] = c3;
            przycisk[4] = c4;
            przycisk[5] = c5;
            przycisk[6] = c6;
            przycisk[7] = c7;
            przycisk[8] = c8;
            przycisk[9] = c9;
            przycisk[10] = cA;
            przycisk[11] = cB;
            przycisk[12] = cC;
            przycisk[13] = cD;
            przycisk[14] = cE;
            przycisk[15] = cF;

            for(int i=0;i<przycisk.length;i++){
                if(e.getSource()==przycisk[i]){
                    arg.setText(arg.getText()+i);
                }
            }
        }

    };
    // zdarzenie od przycisku Clear - wyzerowanie stanu kalkulatora
    private ActionListener sluchaczClr = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            stan = Stan.pocz;
            arg.setText("");
            wyn.setText("");
            operand = new BigInteger("0");
        }
    };
    // zdarzenie od przycisku Delete - usunięcie ostatniej cyfry
    private ActionListener sluchaczDel = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            String a = arg.getText();
            if (a.length() > 0) {
                String s = a.substring(0, a.length() - 1);
                if (s.equals("-")) s = "";
                arg.setText(s);
            }
//            stan = Stan.pocz;
        }
    };
    // zdarzenie od przycisku zmiany znaku (-/+) - dodanie/usunięcie znaku minusa na początku liczby
    private ActionListener sluchaczZnak = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            String a = arg.getText();
            if (a.length() == 0) return;
            if (a.charAt(0) == '-') a = a.substring(1, a.length());
            else a = '-' + a;
            arg.setText(a);
        }
    };
    // zdarzenie od przycisku dodawania (+) - zapamiętanie operacji dodawania i argumentu
    private ActionListener sluchaczDodaj = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            działanie = "+";
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                operand = operand.add(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczOdejmij = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            działanie = "-";
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                operand = operand.subtract(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczMnoz = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            działanie = "*";
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                operand = operand.multiply(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczDziel = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            działanie = "/";
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                operand = operand.divide(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczModulo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            działanie = "%";
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                operand = operand.mod(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczPotega = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            działanie = "^";
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                operand = operand.pow(x.intValue());
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczSilnia = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            int wynik = 1;
            if(x.intValue()==0){
                x.add(new BigInteger("1"));
            }
            for (int i = x.intValue(); i > 0; i--) {
                wynik *= i;
            }
            System.out.println("wynik: "+wynik);
            operand = operand.valueOf(wynik);
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private ActionListener sluchaczRowne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            }
            else {
                if(działanie == "+"){
                    System.out.println("dodawanie wykryte");
                    operand = operand.add(x);
                    działanie = "";
                }
                else if(działanie == "-"){
                    System.out.println("odejmowanie wykryte");
                    operand = operand.subtract(x);
                    działanie = "";
                }
                else if(działanie == "*"){
                    operand = operand.multiply(x);
                    działanie = "";
                }
                else if(działanie == "/"){
                    operand = operand.divide(x);
                    działanie = "";
                }
                else if(działanie == "%"){
                    operand = operand.mod(x);
                    działanie = "";
                }
                else if(działanie == "^"){
                    operand = operand.pow(x.intValue());
                    działanie = "";
                }
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private JButton c0 = new JButton("0");
    private JButton c1 = new JButton("1");
    private JButton c2 = new JButton("2");
    private JButton c3 = new JButton("3");
    private JButton c4 = new JButton("4");
    private JButton c5 = new JButton("5");
    private JButton c6 = new JButton("6");
    private JButton c7 = new JButton("7");
    private JButton c8 = new JButton("8");
    private JButton c9 = new JButton("9");
    private JButton cA = new JButton("A");
    private JButton cB = new JButton("B");
    private JButton cC = new JButton("C");
    private JButton cD = new JButton("D");
    private JButton cE = new JButton("E");
    private JButton cF = new JButton("F");
    private JButton clear = new JButton("Clr");
    private JButton delete = new JButton("Del");
    private JButton dodaj = new JButton("+");
    private JButton odejmij = new JButton("-");
    private JButton mnóż = new JButton("*");
    private JButton dziel = new JButton("/");
    private JButton modulo = new JButton("mod");
    private JButton potęga = new JButton("^");
    private JButton znak = new JButton("+/-");
    private JButton silnia = new JButton("!");
    private JButton równe = new JButton("=");
    private JButton op = new JButton(" ");
    private JPanel centralny = new JPanel(new GridLayout(4, 7));
    {
        centralny.add(cC);
        cC.setEnabled(false);
        cC.addActionListener(sluchaczCyfr);
        centralny.add(cD);
        cD.setEnabled(false);
        cD.addActionListener(sluchaczCyfr);
        centralny.add(cE);
        cE.setEnabled(false);
        cE.addActionListener(sluchaczCyfr);
        centralny.add(cF);
        cF.setEnabled(false);
        cF.addActionListener(sluchaczCyfr);
        centralny.add(dodaj);
        dodaj.addActionListener(sluchaczDodaj);
        centralny.add(odejmij);
        odejmij.addActionListener(sluchaczOdejmij);
        centralny.add(clear);
        clear.addActionListener(sluchaczClr);
        centralny.add(c8);
        c8.addActionListener(sluchaczCyfr);
        centralny.add(c9);
        c9.addActionListener(sluchaczCyfr);
        centralny.add(cA);
        cA.setEnabled(false);
        cA.addActionListener(sluchaczCyfr);
        centralny.add(cB);
        cB.setEnabled(false);
        cB.addActionListener(sluchaczCyfr);
        centralny.add(mnóż);
        mnóż.addActionListener(sluchaczMnoz);
        centralny.add(dziel);
        dziel.addActionListener(sluchaczDziel);
        centralny.add(delete);
        delete.addActionListener(sluchaczDel);
        centralny.add(c4);
        c4.addActionListener(sluchaczCyfr);
        centralny.add(c5);
        c5.addActionListener(sluchaczCyfr);
        centralny.add(c6);
        c6.addActionListener(sluchaczCyfr);
        centralny.add(c7);
        c7.addActionListener(sluchaczCyfr);
        centralny.add(potęga);
        potęga.addActionListener(sluchaczPotega);
        centralny.add(modulo);
        modulo.addActionListener(sluchaczModulo);
        centralny.add(równe);
        równe.addActionListener(sluchaczRowne);
        centralny.add(c0);
        c0.addActionListener(sluchaczCyfr);
        centralny.add(c1);
        c1.addActionListener(sluchaczCyfr);
        centralny.add(c2);
        c2.addActionListener(sluchaczCyfr);
        centralny.add(c3);
        c3.addActionListener(sluchaczCyfr);
        centralny.add(znak);
        znak.addActionListener(sluchaczZnak);
        centralny.add(silnia);
        silnia.addActionListener(sluchaczSilnia);
        centralny.add(op);
        op.setEnabled(false);
    }

    // zdarzenie od przycisku radiowego z systemem 2-owym
    ItemListener sluchaczSyst2 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int p = podstawa;
            podstawa = 2;
            c2.setEnabled(false);
            c3.setEnabled(false);
            c4.setEnabled(false);
            c5.setEnabled(false);
            c6.setEnabled(false);
            c7.setEnabled(false);
            c8.setEnabled(false);
            c9.setEnabled(false);
            cA.setEnabled(false);
            cB.setEnabled(false);
            cC.setEnabled(false);
            cD.setEnabled(false);
            cE.setEnabled(false);
            cF.setEnabled(false);
            String a = arg.getText();
            if (a.length() > 0) {
                BigInteger x = new BigInteger(a, p);
                arg.setText(x.toString(podstawa));
            }
            String w = wyn.getText();
            if (w.length() > 0) {
                BigInteger y = new BigInteger(w, p);
                wyn.setText(y.toString(podstawa));
            }
        }
    };
    // zdarzenie od przycisku radiowego z 10
    private ItemListener sluchaczSyst10 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent ev) {
            int p = podstawa;
            podstawa = 10;
            c2.setEnabled(true);
            c3.setEnabled(true);
            c4.setEnabled(true);
            c5.setEnabled(true);
            c6.setEnabled(true);
            c7.setEnabled(true);
            c8.setEnabled(true);
            c9.setEnabled(true);
            cA.setEnabled(false);
            cB.setEnabled(false);
            cC.setEnabled(false);
            cD.setEnabled(false);
            cE.setEnabled(false);
            cF.setEnabled(false);
            String a = arg.getText();
            if (a.length() > 0) {
                BigInteger x = new BigInteger(a, p);
                arg.setText(x.toString(podstawa));
            }
            String w = wyn.getText();
            if (w.length() > 0) {
                BigInteger x = new BigInteger(w, p);
                wyn.setText(x.toString(podstawa));
            }
        }
    };
    // zdarzenie od przycisku radiowego z 16
    private ItemListener sluchaczSyst16 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent ev) {
            int p = podstawa;
            podstawa = 16;
            c2.setEnabled(true);
            c3.setEnabled(true);
            c4.setEnabled(true);
            c5.setEnabled(true);
            c6.setEnabled(true);
            c7.setEnabled(true);
            c8.setEnabled(true);
            c9.setEnabled(true);
            cA.setEnabled(true);
            cB.setEnabled(true);
            cC.setEnabled(true);
            cD.setEnabled(true);
            cE.setEnabled(true);
            cF.setEnabled(true);
            String a = arg.getText();
            if (a.length() > 0) {
                BigInteger x = new BigInteger(a, p);
                arg.setText(x.toString(podstawa).toUpperCase());
            }
            String w = wyn.getText();
            if (w.length() > 0) {
                BigInteger x = new BigInteger(w, p);
                wyn.setText(x.toString(podstawa).toUpperCase());
            }
        }
    };
    private JLabel sys = new JLabel("system");
    private JRadioButton s2 = new JRadioButton("dwójkowy");
    private JRadioButton s10 = new JRadioButton("dziesiętny", true);
    private JRadioButton s16 = new JRadioButton("szesnaskowy");
    private ButtonGroup bg = new ButtonGroup();
    private JPanel prawy = new JPanel(new GridLayout(4, 1));
    {
        prawy.add(sys);
        prawy.add(s2);
        s2.addItemListener(sluchaczSyst2);
        prawy.add(s10);
        s10.addItemListener(sluchaczSyst10);
        prawy.add(s16);
        s16.addItemListener(sluchaczSyst16);
        bg.add(s2);
        bg.add(s10);
        bg.add(s16);
    }

    public Okno() {
        super("kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 180);
        setLocation(90, 90);
        setResizable(false);
        getContentPane().add(arg, BorderLayout.NORTH);
        getContentPane().add(wyn, BorderLayout.SOUTH);
        getContentPane().add(centralny, BorderLayout.CENTER);
        getContentPane().add(prawy, BorderLayout.EAST);
        setVisible(true);
    }
}
