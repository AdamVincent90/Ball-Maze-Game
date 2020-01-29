/**
 Program:   Assignment 2: Application – Ball Maze
 Filename:  CBallMaze.java
 @author:   © Adam Vincent
 Course:    BEng/BSc/HND Computing Year 1
 Module:    CSY1020 Problem Solving & Programming
 Tutor:     Gary Hill
 @version:  2.0 Incorporates Artificial Intelligence!
 Date:      16/04/18
 */




// These are the imports used to help create the application

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.DecimalFormat;


public class CBallMaze extends JFrame implements ActionListener, ChangeListener {

    // This is to create the menu for the application, this menu comes from NILE, credit to Gary Hill.

    Container yourContainer;     												 			//declares the container
    JScrollPane scrollsVH;       												 			//declares the x,y scrolling of the menu
    JMenuBar topMenuBar;         												 			//declares the menu bar which displays items
    JMenu fileMenu, editMenu, hiScoreMenu, helpMenu; 							 			//declares the menu within a main menu
    JMenuItem exitItem, fontItem, foreColor, backColor, helpItem, aboutItem, hiScores; 	//declares the items within the sum menus
    String aboutBoxString = 	( " Application written by\n © Adam Vincent BSc(Hons)\n"
            + " BsC Computing\n"
            + " University of Northampton\n"
            + " Northampton, NN2 6JD,\n United Kingdom\n E-Mail: adam.vincent16@my.northampton.ac.uk\n"
            + " Tel.(Work) +447903016783\n"
            + " Student Number: 16441282") ;


    // Declared JButton array, this creates the maze using the String arrays listed below.

    private JButton[] panelButton = new JButton[208];


    // Globally declared int values which are called upon opening the program.

    private int ballPosition = 15;
    private int prevPosition = ballPosition;
    private int ticks = 0;
    private int hiscore = 0;


    // 6x JTextFields to display various information for the application.

    private JTextField textField;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textFieldMins;
    private JTextField textFieldSecs;
    private JTextField textFieldHours;


    // 6x JLabels which are used to display what the JTextField represents, for the timers purpose, to create a ":" gap between textfields.

    private JLabel jLOption;
    private JLabel jLSquare;
    private JLabel jLDirection;
    private JLabel jLColon;
    private JLabel jLTimer;
    private JLabel jLSpeed;


    // 19x JButtons which are used for various different events for the application.

    private JButton jLOption1;
    private JButton jLOption2;
    private JButton jLOption3;
    private JButton jBExit;
    private JButton jLUp;
    private JButton jLRight;
    private JButton jLDown;
    private JButton jLLeft;
    private JButton jLBlank1;
    private JButton jLBlank2;
    private JButton jLBlank3;
    private JButton jLBlank4;
    private JButton jLBlank5;
    private JButton jBStart;
    private JButton jBAutoRun;
    private JButton jBReset;
    private JButton jBAct;
    private JButton compass;
    private JButton goldenball;


    // 7x JPanels which is added to the window

    private JPanel panel;
    private JPanel JPBottom;
    private JPanel JPRight;
    private JPanel JPRightTop;
    private JPanel JPRightTimer;
    private JPanel JPRightNav;
    private JPanel JPRightOption;


    // 1x JSlider for the bottom panel.

    private JSlider jSlider;


    // Timer is used for the digital timer, autoRunTimer is used for the auto run button to move the ball automatically as set intervals.

    private Timer timer = new Timer(1000, this);
    private Timer autoRunTimer = new Timer(700, this);


    // Declared and used to display the digital timer in 2 digits only.

    private DecimalFormat digit = new DecimalFormat("00");


    // Declared booleans for the purpose of checking which level is to be loaded.

    boolean lev1 = true;
    boolean lev2 = false;
    boolean lev3 = false;


    // These images are declared for the application

    private ImageIcon SandImage;
    private ImageIcon WhiteImage;
    private ImageIcon CompassWest;
    private ImageIcon CompassSouth;
    private ImageIcon CompassEast;
    private ImageIcon CompassNorth;
    private ImageIcon Goldball;
    private ImageIcon StoneImage;
    private ImageIcon GrayImage;
    private ImageIcon BlackImage;



    // All of the string arrays "Option 1, Option 2, and Option 3 have a total of 207 different string names assigned,
    // these will used to when creating the different levels within the application.

    private String Option1[] = {"sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white",
            "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white",
            "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "sand", "white", "white", "white", "white",
            "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "sand", "white", "white", "white", "white",
            "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white",
            "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white",
            "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white",
            "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white",
            "goal", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand"};

    private String Option2[] = {"sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "sand", "white",
            "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "sand", "white",
            "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white",
            "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white",
            "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
            "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
            "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
            "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white",
            "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white",
            "goal", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand"};

    private String Option3[] = {"white", "sand", "sand", "sand", "white", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "white",
            "white", "sand", "white", "sand", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
            "white", "sand", "white", "sand", "white", "sand", "white", "white", "sand", "sand", "sand", "white", "white", "sand", "white",
            "white", "sand", "white", "sand", "white", "sand", "white", "sand", "sand", "white", "sand", "sand", "sand", "sand", "white",
            "white", "sand", "white", "sand", "sand", "sand", "white", "sand", "white", "white", "white", "white", "white", "white", "white",
            "white", "sand", "white", "white", "white", "white", "white", "sand", "white", "sand", "sand", "sand", "sand", "sand", "white",
            "white", "sand", "sand", "sand", "white", "white", "white", "sand",  "white", "sand", "white", "white", "white", "sand", "white",
            "white", "white", "white", "sand", "white", "white", "white", "white", "white", "sand", "white", "sand", "sand", "sand", "white",
            "white", "sand", "sand", "sand", "white", "sand", "sand", "sand", "white", "sand", "white", "sand", "white", "white", "white",
            "white", "sand", "white", "white", "white", "sand", "white", "sand", "white", "sand", "white", "sand", "white", "white", "white",
            "white", "sand", "sand", "sand", "sand", "sand", "white", "sand", "sand", "sand", "white", "sand", "sand", "sand", "white",
            "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
            "goal", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand"};



    // This string is an array in which is used for the change of background and foreground colours.

    private String editOptions[] = {"Blue", "White", "Red", "Gray", "Green", "Orange"};




    // The main includes creating the class of CBallMaze and setting particular options of the frame such as visibility to the user
    // as well as including the call of the image to be displayed as the main icon image.

    public static void main (String[] args) {

        Image img = Toolkit.getDefaultToolkit().getImage("images/gold-ball.png");
        System.out.println(System.getProperty("user.dir"));

        JOptionPane.showMessageDialog(null, "Welcome to CBall Maze Application!");

        CBallMaze frame = new CBallMaze();

        frame.setSize(775, 650);
        frame.createGUI();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Ball Maze Application");
        frame.setLocationRelativeTo(null);
        frame.setIconImage(img);


    }



    // This method creates the GUI for all the panels

    private void createGUI() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new BorderLayout() );

        jPanel(window);
        jPBottom(window);
        jPRight(window);
        jPRightTop(window);
        jPRightTimer(window);
        jPRightNav(window);
        jPRightOption(window);
        jPRightOption1(window);
        jPRightControl(window);
        jPRightStats(window);
        jPRightTimerStat(window);
        jPSlider(window);
        Compass(window);
        menuSetup(window);

    }



    // This method calls the bottom panel which is added to the window.

    public void jPBottom(Container window) {

        JPBottom = new JPanel();
        JPBottom.setPreferredSize(new Dimension(745, 60));
        JPBottom.setBackground(Color.white);
        window.add(JPBottom, BorderLayout.SOUTH);
        JPBottom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }



    // This calls the centre panel to the window is with the dimensions of 590, 550. This is also added to the window.

    public void jPanel(Container window) {

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(590, 550));
        panel.setBackground(Color.white);
        window.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(13,16));
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panelButton(window);

    }



    // This method creates a grid layout of 3x2 in which is added to the right panel, this also adds the jPRightStats to this layout.

    public void jPRightTop(Container window) {

        JPRightTop = new JPanel();
        JPRightTop.setPreferredSize(new Dimension(150, 100));
        JPRightTop.setBackground(Color.white);
        JPRight.add(JPRightTop);
        JPRightTop.setLayout(new GridLayout(3,2));
        JPRightTop.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JPRight.add(JPRightTop, BorderLayout.CENTER);

    }



    // This creates the right panel of the application in which is added to the window.

    public void jPRight(Container window) {

        JPRight = new JPanel();
        JPRight.setPreferredSize(new Dimension(180,550));
        JPRight.setBackground(Color.white);
        window.add(JPRight, BorderLayout.EAST);
        JPRight.setBorder(BorderFactory.createLineBorder(Color.black, 2));

    }


    // This Method adds the timer stats method to this method in which adds to the right panel.

    public void jPRightTimer(Container window){

        JPRightTimer = new JPanel();
        JPRightTimer.setPreferredSize(new Dimension(150, 50));
        JPRightTimer.setBackground(Color.white);
        JPRight.add(JPRightTimer);
        JPRightTimer.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JPRight.add(JPRightTimer, BorderLayout.CENTER);

    }



    // This creates a 3x3 grid layout for the jPRightControl buttons.

    public void jPRightNav(Container window) {

        JPRightNav = new JPanel();
        JPRightNav.setPreferredSize(new Dimension(150, 120));
        JPRightNav.setBackground(Color.white);
        JPRight.add(JPRightNav);
        JPRightNav.setLayout(new GridLayout(3,3));
        JPRightNav.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JPRight.add(JPRightNav, BorderLayout.CENTER);

    }



    // This method creates A grid layout of 2x2 for the jPrightOption1 buttons.

    public void jPRightOption(Container window)  {

        JPRightOption = new JPanel();
        JPRightOption.setPreferredSize(new Dimension(150, 100));
        JPRightOption.setBackground(Color.white);
        JPRight.add(JPRightOption);
        JPRightOption.setLayout(new GridLayout(2,2));
        JPRightOption.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JPRight.add(JPRightOption, BorderLayout.CENTER);

    }



    // This method creates the buttons, Option 1, Option 2, Option 3, and exit which is added to the JPRightOption

    public void jPRightOption1(Container window) {

        jLOption1 = new JButton("Option 1");
        JPRightOption.add(jLOption1);
        jLOption1.setFont(new Font("Serif", Font.BOLD, 10));
        jLOption1.setBackground(Color.white);
        jLOption1.addActionListener(this);

        jLOption2 = new JButton("Option 2");
        JPRightOption.add(jLOption2);
        jLOption2.setFont(new Font("Serif", Font.BOLD, 10));
        jLOption2.setBackground(Color.white);
        jLOption2.addActionListener(this);

        jLOption3 = new JButton("Option 3");
        JPRightOption.add(jLOption3);
        jLOption3.setFont(new Font("Serif", Font.BOLD, 10));
        jLOption3.setBackground(Color.white);
        jLOption3.addActionListener(this);

        jBExit = new JButton("Exit");
        JPRightOption.add(jBExit);
        jBExit.setBackground(Color.white);
        jBExit.setFont(new Font("Serif", Font.BOLD, 10));
        jBExit.addActionListener(this);
        jBExit.setToolTipText("Click on this button to exit the application");

    }



    // This create the buttons for the JPRightNav grid layout.

    public void jPRightControl(Container window) {

        jLBlank1 = new JButton(" ");
        JPRightNav.add(jLBlank1);
        jLBlank1.setBackground(Color.white);
        jLBlank1.setEnabled(false);

        jLUp = new JButton("^");
        JPRightNav.add(jLUp);
        jLUp.setBackground(Color.white);
        jLUp.addActionListener(this);
        jLUp.setEnabled(false);

        jLBlank2 = new JButton(" ");
        JPRightNav.add(jLBlank2);
        jLBlank2.setBackground(Color.white);
        jLBlank2.setEnabled(false);

        jLLeft = new JButton("<");
        JPRightNav.add(jLLeft);
        jLLeft.setBackground(Color.white);
        jLLeft.addActionListener(this);
        jLLeft.setEnabled(false);

        jLBlank3 = new JButton(" ");
        JPRightNav.add(jLBlank3);
        jLBlank3.setBackground(Color.white);
        jLBlank3.setEnabled(false);

        jLRight = new JButton(">");
        JPRightNav.add(jLRight);
        jLRight.setBackground(Color.white);
        jLRight.addActionListener(this);
        jLRight.setEnabled(false);

        jLBlank4 = new JButton(" ");
        JPRightNav.add(jLBlank4);
        jLBlank4.setBackground(Color.white);
        jLBlank4.setEnabled(false);

        jLDown = new JButton("v");
        JPRightNav.add(jLDown);
        jLDown.setBackground(Color.white);
        jLDown.addActionListener(this);
        jLDown.setEnabled(false);

        jLBlank5 = new JButton(" ");
        JPRightNav.add(jLBlank5);
        jLBlank5.setBackground(Color.white);
        jLBlank5.setEnabled(false);

    }



    // This is the method that calls 3 JLabels and 3 JTextFields to create the "Option, Direction and Square" JLabels and
    // textFields which adds it to the right hand panel

    public void jPRightStats(Container window) {

        jLOption = new JLabel("Option: ");
        JPRightTop.add(jLOption, BorderLayout.WEST);

        textField1 = new JTextField("  1  ");
        JPRightTop.add(textField1);
        textField1.setEditable(false);

        jLSquare = new JLabel("Sqaure: ");
        JPRightTop.add(jLSquare);

        textField = new JTextField("  15  ");
        JPRightTop.add(textField);
        textField.setEditable(false);

        jLDirection = new JLabel("Direction: ");
        JPRightTop.add(jLDirection, BorderLayout.WEST);

        textField3 = new JTextField("  South  ");
        JPRightTop.add(textField3);
        textField3.setEditable(false);

    }



    // This method calls 3 small text fields and 3 JLabels in order to create a digital timer

    public void jPRightTimerStat(Container window)
    {
        jLTimer = new JLabel("_______DIGITAL TIMER_______");
        JPRightTimer.add(jLTimer);

        textFieldHours = new JTextField("00");
        JPRightTimer.add(textFieldHours);
        textFieldHours.setEditable(false);

        jLColon = new JLabel(":");
        JPRightTimer.add(jLColon);

        textFieldMins = new JTextField("00");
        JPRightTimer.add(textFieldMins);
        textFieldMins.setEditable(false);

        jLColon = new JLabel(":");
        JPRightTimer.add(jLColon);

        textFieldSecs = new JTextField("00");
        JPRightTimer.add(textFieldSecs);
        textFieldSecs.setEditable(false);

    }



    // This method calls the Slider and adds the slider to the bottom panel
    // This method also adds JButtons to the bottom panel

    public void jPSlider(Container window) {

        jLSpeed = new JLabel("Speed:");
        JPBottom.add(jLSpeed);

        jSlider = new JSlider(SwingConstants.HORIZONTAL,0,90,45);
        JPBottom.add(jSlider, BorderLayout.EAST);
        jSlider.setPreferredSize(new Dimension(300, 45));
        jSlider.setPaintTicks(true);
        jSlider.setMajorTickSpacing(15);
        jSlider.setBackground(Color.white);
        jSlider.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jSlider.addChangeListener(this);
        jSlider.setToolTipText("Adjust the speed: \n 0 - 15 very slow: \n 16 - 30 slow:"
                + " \n 31 - 60 medium \n 61 - 75 fast \n 76 - 90 very fast   " );

        jBStart = new JButton(" Manual Run");
        JPBottom.add(jBStart);
        jBStart.setBackground(Color.white);
        jBStart.addActionListener(this);

        jBAutoRun = new JButton("Run");
        JPBottom.add(jBAutoRun);
        jBAutoRun.setBackground(Color.white);
        jBAutoRun.addActionListener(this);

        jBReset = new JButton("Reset");
        JPBottom.add(jBReset);
        jBReset.setBackground(Color.white);
        jBReset.addActionListener(this);
        jBReset.setEnabled(false);

        jBAct = new JButton("Act");
        JPBottom.add(jBAct);
        jBAct.setBackground(Color.white);
        jBAct.addActionListener(this);

    }



    // This method calls the pictures of the backgrounds as well as calling an array of buttons to be added to the centre panel of the frame

    public void panelButton(Container window) {

        try {
            WhiteImage = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource
                    ("images/whiteLarge.png")));
            GrayImage = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource
                    ("images/grayLarge.jpg")));
            BlackImage = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource
                    ("images/blackLarge.png")));
            SandImage = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource
                    ("images/sand.jpg")));
            StoneImage = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource
                    ("images/sandstone.jpg")));
        }

        catch (Exception e) {
            System.err.println("Image Error " + e);
        }


        for (int loop = 0; loop < 208; loop++) {

            if (lev1 == true) {

                WhiteBlock(loop);
                Level1(loop);
                GoldenBall(loop);

            }

            if (lev2 == true) {

                GrayBlock(loop);
                Level2(loop);
                GoldenBall(loop);
            }

            if (lev3 == true) {

                BlackBlock(loop);
                Level3(loop);
                GoldenBall(loop);

            }

        }

        panelButton[ballPosition].setIcon(Goldball);
    }



    // This method removes everything of the main panel (where the maze layout is made before the program calling the background
    // methods as well as the level methods.

    public void removeLevel(JPanel panel) {

        panel.removeAll();

    }



    // Calls the background of the maze if the user selects option 1, this method is run by default upon opening.

    public void WhiteBlock(int loop) {

        panelButton[loop] = new JButton("");
        panelButton[loop].setBackground(Color.white);
        panelButton[loop].setMargin(new Insets(0,0,0,0));
        panelButton[loop].setBorder(null);
        panel.add(panelButton[loop]);
        panelButton[loop].setIcon(WhiteImage);


    }



    // Calls the background of the maze if the user selects option 2.

    public void GrayBlock(int loop) {

        panelButton[loop] = new JButton("");
        panelButton[loop].setBackground(new Color(211,211,211));
        panelButton[loop].setMargin(new Insets(0,0,0,0));
        panelButton[loop].setBorder(null);
        panel.add(panelButton[loop]);
        panelButton[loop].setIcon(GrayImage);

    }



    // Calls the background of the maze if the user selects option 3.

    public void BlackBlock(int loop) {

        panelButton[loop] = new JButton("");
        panelButton[loop].setBackground(Color.black);
        panelButton[loop].setMargin(new Insets(0,0,0,0));
        panelButton[loop].setBorder(null);
        panel.add(panelButton[loop]);
        panelButton[loop].setIcon(BlackImage);
    }



    // Calls the level 1 method if the user selects option 1, this level is default upon opening.

    public void Level1(int loop) {


        if (Option1[loop].equals("sand")) {

            panelButton[loop].setIcon(SandImage);
        }

        if (Option1[loop].equals("goal")) {

            panelButton[loop].setIcon(StoneImage);
        }

    }



    // Calls the level 2 method if the user selects option 2.

    public void Level2(int loop) {

        if (Option2[loop].equals("sand")) {

            panelButton[loop].setIcon(SandImage);
        }

        if (Option2[loop].equals("goal")) {

            panelButton[loop].setIcon(StoneImage);
        }

    }



    // Calls the level 3 method if the user selects option 3.

    public void Level3(int loop) {

        if (Option3[loop].equals("sand")) {

            panelButton[loop].setIcon(SandImage);
        }

        if (Option3[loop].equals("goal")) {

            panelButton[loop].setIcon(StoneImage);
        }

        panelButton[ballPosition].setIcon(Goldball);

    }



    //creates a golden ball and adds it onto a button

    public void GoldenBall(int loop) {

        try {
            Goldball = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource
                    ("images/sand60x601.png")));
        }
        catch (Exception e) {
            System.err.println("Image Error " + e);
        }

        goldenball = new JButton();
        goldenball.setIcon(Goldball);
        goldenball.setBorder(null);
    }



    // changes the speed of the timer, left makes the timer delay higher, thus a slower time
    // moving the slider to the right allows the timer to tick faster which a slower delay

    public void stateChanged(ChangeEvent event) {

        int speed = jSlider.getValue();
        System.out.println("Slider is at: " +speed);

        if (speed < 16)
        {
            timer.setDelay(1400);
            autoRunTimer.setDelay(1400);
        }

        else if (speed < 31)
        {
            timer.setDelay(1200);
            autoRunTimer.setDelay(1100);
        }

        else if (speed < 61)
        {
            timer.setDelay(1000);
            autoRunTimer.setDelay(700);
        }

        else if (speed < 76)
        {
            timer.setDelay(600);
            autoRunTimer.setDelay(400);
        }

        else if (speed < 91)
        {
            timer.setDelay(400);
            autoRunTimer.setDelay(200);
        }

    }



    // Creates 4 images onto one jButton, each compass direction image is called depending on navigation select

    public void Compass (Container window) {

        try {

            CompassWest = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource				// Adds all images of the compass: West image
                    ("images/west.jpg")));
            CompassSouth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource			// South Image
                    ("images/south.jpg")));
            CompassEast = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource				// East Image
                    ("images/east.jpg")));
            CompassNorth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource			// North Image
                    ("images/north.jpg")));

        }

        catch (Exception e) {
            System.err.println("Image Error " + e);

        }

        compass = new JButton(); // Adds compass to a JButton
        JPRight.add(compass);
        compass.setIcon(CompassSouth);
        compass.setBackground(Color.orange);
        compass.setBorder(BorderFactory.createLineBorder(Color.black));		// Gives image(JButton) a black border

    }



    // Method to call the JMenu which creates the declared JMenu items in which are global to add to the JFrame

    public void menuSetup(Container window) {

        topMenuBar = new JMenuBar();      					//creates a menu bar
        setJMenuBar(topMenuBar);
        topMenuBar.setBackground(Color.white);				//adds the menu bar and sets it to the frame

        fileMenu = new JMenu("File");     					// Standard file menu within the menu bar
        exitItem = new JMenuItem("Exit"); 					//Exit button within the file menu
        fileMenu.add(exitItem);           					//add the items to the menu
        exitItem.addActionListener(this); 					//add the listener to the item
        topMenuBar.add(fileMenu);         					//add the menu to the menu bar

        editMenu = new JMenu("Edit");     					// Standard edit menu within the menu bar
        foreColor = new JMenuItem("Foreground Colour"); 		// foreground colour item
        editMenu.add(foreColor);           					//add the items to the menu
        foreColor.addActionListener(this); 					//add the listener to the item
        backColor = new JMenuItem("Background Colour");		//Background Colour item
        editMenu.add(backColor);           					//add the items to the menu
        backColor.addActionListener(this); 					//add the listener to the item
        topMenuBar.add(editMenu );

        hiScoreMenu = new JMenu("Last Score");
        topMenuBar.add(hiScoreMenu);
        hiScores = new JMenuItem("Your last score");
        hiScoreMenu.add(hiScores);
        hiScores.addActionListener(this);

        helpMenu = new JMenu("Help");   						// Shows details of the application writer
        aboutItem = new JMenuItem("About...");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);
        topMenuBar.add(helpMenu);

    }



    // Changes the background colour of the application (the 3 JPanels) and uses a string array to determine the users choice

    public void backgroundColor() {


        String input = (String) JOptionPane.showInputDialog(null, "Select A background colour!", "", JOptionPane.QUESTION_MESSAGE, null, editOptions, editOptions[1]);

        System.out.print(""+input);

        if (input == "Orange") {

            JPBottom.setBackground(Color.orange);
            JPRight.setBackground(Color.orange);

        }

        if (input == "Red") {

            JPBottom.setBackground(Color.red);
            JPRight.setBackground(Color.red);

        }

        if (input == "White") {

            JPBottom.setBackground(Color.white);
            JPRight.setBackground(Color.white);

        }

        if (input == "Blue") {

            JPBottom.setBackground(Color.blue);
            JPRight.setBackground(Color.blue);

        }

        if (input == "Green") {

            JPBottom.setBackground(Color.green);
            JPRight.setBackground(Color.green);

        }

        if (input == "Gray") {

            JPBottom.setBackground(Color.gray);
            JPRight.setBackground(Color.gray);

        }
    }



    // Changes the foreground colour of the application to allow the user to freely select a desired colour through using a string array and matching the string
    // array to to which colour it will be altered too

    public void foregroundColor() {

        String input = (String) JOptionPane.showInputDialog(null, "Select A foreground colour!", "", JOptionPane.QUESTION_MESSAGE, null, editOptions, editOptions[1]);

        System.out.print(""+input);

        if (input == "Orange") {

            JPRightTop.setBackground(Color.orange);
            JPRightTimer.setBackground(Color.orange);
            JPRightNav.setBackground(Color.orange);
            JPRightTop.setBackground(Color.orange);
            jLOption1.setBackground(Color.orange);
            jLOption2.setBackground(Color.orange);
            jLOption3.setBackground(Color.orange);
            jBExit.setBackground(Color.orange);
            jSlider.setBackground(Color.orange);
            jLBlank1.setBackground(Color.orange);
            jLBlank2.setBackground(Color.orange);
            jLBlank3.setBackground(Color.orange);
            jLBlank4.setBackground(Color.orange);
            jLBlank5.setBackground(Color.orange);

            topMenuBar.setBackground(Color.orange);

        }

        if (input == "Red") {

            JPRightTop.setBackground(Color.red);
            JPRightTimer.setBackground(Color.red);
            JPRightNav.setBackground(Color.red);
            JPRightTop.setBackground(Color.red);
            jLOption1.setBackground(Color.red);
            jLOption2.setBackground(Color.red);
            jLOption3.setBackground(Color.red);
            jBExit.setBackground(Color.red);
            jSlider.setBackground(Color.red);
            jLBlank1.setBackground(Color.red);
            jLBlank2.setBackground(Color.red);
            jLBlank3.setBackground(Color.red);
            jLBlank4.setBackground(Color.red);
            jLBlank5.setBackground(Color.red);

            topMenuBar.setBackground(Color.red);


        }

        if (input == "White") {

            JPRightTop.setBackground(Color.white);
            JPRightTimer.setBackground(Color.white);
            JPRightNav.setBackground(Color.white);
            JPRightTop.setBackground(Color.white);
            jLOption1.setBackground(Color.white);
            jLOption2.setBackground(Color.white);
            jLOption3.setBackground(Color.white);
            jBExit.setBackground(Color.white);
            jSlider.setBackground(Color.white);
            jLBlank1.setBackground(Color.white);
            jLBlank2.setBackground(Color.white);
            jLBlank3.setBackground(Color.white);
            jLBlank4.setBackground(Color.white);
            jLBlank5.setBackground(Color.white);

            topMenuBar.setBackground(Color.white);

        }

        if (input == "Blue") {

            JPRightTop.setBackground(Color.blue);
            JPRightTimer.setBackground(Color.blue);
            JPRightNav.setBackground(Color.blue);
            JPRightTop.setBackground(Color.blue);
            jLOption1.setBackground(Color.blue);
            jLOption2.setBackground(Color.blue);
            jLOption3.setBackground(Color.blue);
            jBExit.setBackground(Color.blue);
            jSlider.setBackground(Color.blue);
            jLBlank1.setBackground(Color.blue);
            jLBlank2.setBackground(Color.blue);
            jLBlank3.setBackground(Color.blue);
            jLBlank4.setBackground(Color.blue);
            jLBlank5.setBackground(Color.blue);

            topMenuBar.setBackground(Color.blue);
        }

        if (input == "Green") {

            JPRightTop.setBackground(Color.green);
            JPRightTimer.setBackground(Color.green);
            JPRightNav.setBackground(Color.green);
            JPRightTop.setBackground(Color.green);
            jLOption1.setBackground(Color.green);
            jLOption2.setBackground(Color.green);
            jLOption3.setBackground(Color.green);
            jBExit.setBackground(Color.green);
            jSlider.setBackground(Color.green);
            jLBlank1.setBackground(Color.green);
            jLBlank2.setBackground(Color.green);
            jLBlank3.setBackground(Color.green);
            jLBlank4.setBackground(Color.green);
            jLBlank5.setBackground(Color.green);

            topMenuBar.setBackground(Color.green);

        }

        if (input == "Gray") {

            JPRightTop.setBackground(Color.gray);
            JPRightTimer.setBackground(Color.gray);
            JPRightNav.setBackground(Color.gray);
            JPRightTop.setBackground(Color.gray);
            jLOption1.setBackground(Color.gray);
            jLOption2.setBackground(Color.gray);
            jLOption3.setBackground(Color.gray);
            jBExit.setBackground(Color.gray);
            jSlider.setBackground(Color.gray);
            jLBlank1.setBackground(Color.gray);
            jLBlank2.setBackground(Color.gray);
            jLBlank3.setBackground(Color.gray);
            jLBlank4.setBackground(Color.gray);
            jLBlank5.setBackground(Color.gray);

            topMenuBar.setBackground(Color.gray);

        }

    }



    //method is run upon the user selecting the exit button within the file menu or the exit button

    public void exit() {

        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", null, JOptionPane.YES_NO_CANCEL_OPTION);

        if (input == JOptionPane.YES_OPTION) {
            System.exit(0);

        }
    }



    // method runs when the user selects run or auto run buttons
    // timer starts and sets various buttons to be enabled and disabled

    public void start() {

        timer.start();
        hiscore = 0;

        jLUp.setEnabled(true);
        jLDown.setEnabled(true);
        jLRight.setEnabled(true);
        jLLeft.setEnabled(true);
        jBReset.setEnabled(true);
        jBStart.setEnabled(false);
        jLOption1.setEnabled(false);
        jLOption2.setEnabled(false);
        jLOption3.setEnabled(false);
        jBAutoRun.setEnabled(false);
        jBAct.setEnabled(false);

    }



    // displays the last score the timer records when the user used the run button

    public void yourHighScore() {

        JOptionPane.showMessageDialog(null,"Your last score is: " +hiscore +" seconds!");

    }



    // to move the golden ball left

    public void moveLeft() {

        textField3.setText("  West  ");
        compass.setIcon(CompassWest);


        if (lev1 == true) {

            if (Option1[ballPosition - 1].equals("sand") || (Option1[ballPosition - 1].equals("goal"))) {

                ballPosition--;
                prevPosition = ballPosition +1;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);


            }

            if (ballPosition == 9 || ballPosition == 54 || ballPosition == 101 || ballPosition == 146
                    || ballPosition == 150) {


                ballPosition = ballPosition + 48;
                prevPosition = ballPosition -48;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField3.setText("  South  ");
                compass.setIcon(CompassSouth);
                textField.setText(""+ballPosition);
            }

            if (ballPosition == 192) {

                levelReset();

                JOptionPane.showMessageDialog(null, "Maze completed, see last score tab to view your last score! \n"
                        + "Note: non manual run routines do not record previous scores.");
            }
        }

        if (lev2 == true) {

            if (Option2[ballPosition - 1].equals("sand") || (Option2[ballPosition - 1].equals("goal"))) {

                ballPosition--;
                prevPosition = ballPosition +1;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);

                if (ballPosition == 192) {

                    levelReset();

                    JOptionPane.showMessageDialog(null, "Maze completed, level resetted. see last score tab to view your last score! \n"
                            + "Note: Auto run routines do not record previous scores.");
                }

            }

            if (ballPosition == 154 || ballPosition == 146 || ballPosition == 49 || ballPosition == 102) {


                ballPosition = ballPosition + 48;
                prevPosition = ballPosition -48;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField3.setText("  South  ");
                compass.setIcon(CompassSouth);
                textField.setText(""+ballPosition);
            }
        }

        if (lev3 == true) {

            if (Option3[ballPosition - 1].equals("sand") || (Option3[ballPosition - 1].equals("goal"))) {

                ballPosition--;
                prevPosition = ballPosition +1;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);

            }

        }
    }



    // to move the golden ball right

    public void moveRight() {

        textField3.setText("  East  ");
        compass.setIcon(CompassEast);

        if (lev1 == true) {

            if (Option2[ballPosition + 1].equals("sand")) {

                ballPosition++;
                prevPosition = ballPosition -1;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);
            }

            if (ballPosition == 59 || ballPosition == 108 || ballPosition == 150) {

                ballPosition = ballPosition + 48;
                prevPosition = ballPosition -48;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField3.setText("  South  ");
                compass.setIcon(CompassSouth);
                textField.setText(""+ballPosition);
            }
        }

        if (lev2 == true) {

            if (Option2[ballPosition + 1].equals("sand")) {

                ballPosition++;
                prevPosition = ballPosition -1;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);

                if (ballPosition == 7 || ballPosition == 57 || ballPosition == 110 || ballPosition == 102 ||
                        ballPosition == 154) {

                    ballPosition = ballPosition + 48;
                    prevPosition = ballPosition -48;
                    panelButton[ballPosition].setIcon(Goldball);
                    panelButton[prevPosition].setIcon(SandImage);
                    textField3.setText("  South  ");
                    compass.setIcon(CompassSouth);
                    textField.setText(""+ballPosition);
                }

                if (ballPosition == 200) {

                    ballPosition = ballPosition + 32;
                    prevPosition = ballPosition -32;
                    panelButton[ballPosition].setIcon(Goldball);
                    panelButton[prevPosition].setIcon(SandImage);
                    textField3.setText("  South  ");
                    compass.setIcon(CompassSouth);
                    textField.setText(""+ballPosition);
                }
            }
        }

        if (lev3 == true) {

            if (Option3[ballPosition + 1].equals("sand")) {

                ballPosition++;
                prevPosition = ballPosition -1;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);
            }
        }
    }



    // to move the golden ball down

    public void moveDown() {

        textField3.setText("  South  ");
        compass.setIcon(CompassSouth);

        if (lev3 == true) {

            if (Option3[ballPosition + 15].equals("sand")) {

                ballPosition = ballPosition + 15;
                prevPosition = ballPosition - 15;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);
            }
        }
    }



    // to move the golden ball up

    public void moveUp() {

        textField3.setText("  North  ");
        compass.setIcon(CompassNorth);

        if (lev3 == true) {

            if (Option3[ballPosition - 15].equals("sand")) {

                ballPosition = ballPosition - 15;
                prevPosition = ballPosition + 15;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[prevPosition].setIcon(SandImage);
                textField.setText(""+ballPosition);
            }
        }

    }



    // method runs when the user selects the auto run button
    // calls the start method and sets various buttons to false
    // uses a boolean to check which option the user is on
    // calls a method depending on which boolean is true

    public void autoRun() {
        start();
        jBReset.setEnabled(false);
        jBAutoRun.setEnabled(false);
        jBAct.setEnabled(false);

        if (lev1 == true) {

            actLevelOne();
        }

        if (lev2 == true) {

            actLevelTwo();
        }

    }



    // calls when the user reaches the stone block on the level
    // checks which level the user is on
    // stops the timer and resets the timer value back to 0

    public void levelEnd() {

        if (lev1 == true || lev2 == true) {


            if (ballPosition == 192) {

                timer.stop();
                ticks = 0;

                jLUp.setEnabled(false);
                jLDown.setEnabled(false);
                jLRight.setEnabled(false);
                jLLeft.setEnabled(false);
                jBReset.setEnabled(false);
                jBStart.setEnabled(true);
                jLOption1.setEnabled(true);
                jLOption2.setEnabled(true);
                jLOption3.setEnabled(true);
                jBAutoRun.setEnabled(true);
                jBAct.setEnabled(true);

                if (lev1 == true) {

                    ballPosition = 15;
                    panelButton[15].setIcon(Goldball);
                    panelButton[192].setIcon(StoneImage);
                    textField.setText(""+ballPosition);
                    textFieldHours.setText("00");
                    textFieldSecs.setText("00");
                    textFieldMins.setText("00");

                }

                if (lev2 == true) {

                    ballPosition = 0;
                    panelButton[0].setIcon(Goldball);
                    panelButton[192].setIcon(StoneImage);
                    textField.setText(""+ballPosition);
                    textFieldHours.setText("00");
                    textFieldSecs.setText("00");
                    textFieldMins.setText("00");

                }
            }
        }

        if (lev3 == true) {

            if (ballPosition == 180) {

                timer.stop();
                JOptionPane.showMessageDialog(null, "Congratulation space man! Your time is: \n                 " +ticks + " seconds");
                ticks = 0;

                jLUp.setEnabled(false);
                jLDown.setEnabled(false);
                jLRight.setEnabled(false);
                jLLeft.setEnabled(false);
                jBReset.setEnabled(false);
                jBStart.setEnabled(true);
                jLOption1.setEnabled(true);
                jLOption2.setEnabled(true);
                jLOption3.setEnabled(true);

                ballPosition = 97;
                panelButton[ballPosition].setIcon(Goldball);
                panelButton[180].setIcon(StoneImage);
                textField.setText(""+ballPosition);
                textFieldHours.setText("00");
                textFieldSecs.setText("00");
                textFieldMins.setText("00");
                textField3.setText("  North  ");
                compass.setIcon(CompassNorth);

            }

        }
    }



    // method called with the levelEnd method
    // this method resets the GUI back to its original position

    public void levelReset() {

        if (lev1 == true) {

            panelButton[ballPosition].setIcon(SandImage);
            ballPosition = 15;
            textField.setText("15");
            panelButton[ballPosition].setIcon(Goldball);
            panelButton[192].setIcon(StoneImage);
            autoRunTimer.stop();

        }

        if (lev2 == true) {

            panelButton[ballPosition].setIcon(SandImage);
            ballPosition = 0;
            textField.setText("0");
            panelButton[ballPosition].setIcon(Goldball);
            panelButton[192].setIcon(StoneImage);
            autoRunTimer.stop();

        }

        if (lev3 == true) {

            panelButton[ballPosition].setIcon(SandImage);
            ballPosition = 97;
            textField.setText("97");
            panelButton[ballPosition].setIcon(Goldball);
            panelButton[180].setIcon(StoneImage);

        }

        textFieldHours.setText("00");
        textFieldSecs.setText("00");
        textFieldMins.setText("00");
        ticks = 0;
        timer.stop();
        jLUp.setEnabled(false);
        jLDown.setEnabled(false);
        jLRight.setEnabled(false);
        jLLeft.setEnabled(false);
        jBReset.setEnabled(false);
        jBStart.setEnabled(true);
        jLOption1.setEnabled(true);
        jLOption2.setEnabled(true);
        jLOption3.setEnabled(true);
        jBAutoRun.setEnabled(true);
        jBAct.setEnabled(true);
        JOptionPane.showMessageDialog(null, "level has been reset.");


    }



    // method called upon the user selecting the Auto Run or Act button
    // calls a method to move left as the ball only needs to move left to finish the level
    // call the level reset and level end method when the golden ball reaches the stone block

    public void actLevelOne() {

        moveLeft();

        if (ballPosition == 192) {
            levelReset();
            levelEnd();
            jBAutoRun.setEnabled(true);
            jBAct.setEnabled(true);

        }

    }



    // method is called if the user is on option 2, and the user selects auto run or act
    // calls the move left method when the ball position is less than 100
    // calls the move right method when the ball position is over 100

    public void actLevelTwo() {


        if (ballPosition == 192) {

            levelReset();
            levelEnd();
            jBAutoRun.setEnabled(true);
            jBAct.setEnabled(true);
        }

        else if (Option2[ballPosition + 1].equals("sand") && ballPosition < 100) {
            moveRight();
        }

        else if (Option2[ballPosition - 1].equals("sand") || Option2[ballPosition - 1].equals("goal")) {
            moveLeft();
        }

    }



    // This method calls the action events of other methods depending on the user left click input.

    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();


        if (source == jBExit) {

            exit();
        }

        // Option buttons are not yet converted to methods

        if (source == jLOption1) {

            textField1.setText("  1  ");
            panel.setBackground(Color.white);

            ballPosition = 15;
            textField.setText(""+ballPosition);

            lev1 = true;
            lev2 = false;
            lev3 = false;

            jLOption1.setEnabled(false);
            jLOption2.setEnabled(true);
            jLOption3.setEnabled(true);
            jBAct.setEnabled(true);
            jBAutoRun.setEnabled(true);

            removeLevel(panel);
            panelButton(panel);

        }

        if (source == jLOption2) {

            textField1.setText("  2  ");
            panel.setBackground(new Color(188, 192, 196));

            ballPosition = 0;
            textField.setText(""+ballPosition);

            lev1 = false;
            lev2 = true;
            lev3 = false;

            jLOption1.setEnabled(true);
            jLOption2.setEnabled(false);
            jLOption3.setEnabled(true);
            jBAct.setEnabled(true);
            jBAutoRun.setEnabled(true);

            removeLevel(panel);
            panelButton(panel);

        }

        if (source == jLOption3) {

            JOptionPane.showMessageDialog(null, "Level 3 allows you to move the ball up and down, gravity does not apply in space! \n Note: Act and Auto Run buttons are disabled for this level.");

            textField1.setText("  3  ");
            panel.setBackground(Color.black);

            ballPosition = 97;
            textField.setText(""+ballPosition);

            lev1 = false;
            lev2 = false;
            lev3 = true;

            jLOption1.setEnabled(true);
            jLOption2.setEnabled(true);
            jLOption3.setEnabled(false);
            jBAct.setEnabled(false);
            jBAutoRun.setEnabled(false);

            removeLevel(panel);
            panelButton(panel);

        }

        if (source == jBStart) {

            start();

        }

        if (source == jBAutoRun) {

            JOptionPane.showMessageDialog(null, "Run feature activated, allow the ball to finish the maze.");
            autoRunTimer.start();

        }

        if (source == jBAct) {

            if (lev1 == true) {

                actLevelOne();
            }

            if (lev2 == true) {

                actLevelTwo();
            }

        }

        if (source == jLUp) {

            moveUp();
            levelEnd();
        }

        if (source == jLDown) {

            moveDown();
            levelEnd();
        }

        if (source == jLRight) {

            moveRight();
            levelEnd();
        }

        else if (source == jLLeft) {

            moveLeft();
            levelEnd();
        }


        if (source == jBReset) {

            ticks = 0;
            hiscore = ticks;
            levelReset();
        }

        if (source == timer) {

            ticks++;
            hiscore = ticks;
            textFieldHours.setText(digit.format((ticks / 3600) % 24));
            textFieldSecs.setText(digit.format(ticks % 60));
            textFieldMins.setText(digit.format((ticks / 60)% 60));
        }

        if (source == autoRunTimer) {

            autoRun();

        }

        if (source == aboutItem) {

            JOptionPane.showMessageDialog(null, ""+aboutBoxString);
        }

        if (source == exitItem) {

            exit();
        }

        if (source == backColor) {

            backgroundColor();
        }

        if (source == foreColor) {

            foregroundColor();
        }

        if (source == hiScores) {

            yourHighScore();
        }

    }

}
