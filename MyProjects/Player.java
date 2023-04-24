
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;

class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}

public class Player extends JFrame implements ActionListener {
    String[] musics = new String[50];
    String[] liked = new String[50];
    int count1=0;
    int count2 = 0;
    JButton select;
    JButton play;
    JButton stop;
    JButton restart;
    String music_name = "";
    JTextField input;
    JLabel myLabel;
    Player() throws IOException {
        
        BufferedReader br2 = new BufferedReader(new FileReader("B:/Projects/Sasta Spotify/Project/src/Resources/liked.txt"));
        try {
            String line = br2.readLine();
            while (line != null) {
                liked[count2] = line;
                line = br2.readLine();
                count2++;
            }
        } finally {
            br2.close();
        }

        BufferedReader br = new BufferedReader(new FileReader("B:/Projects/Sasta Spotify/Project/src/Resources/musics.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                musics[count1] = line;
                line = br.readLine();
                count1++;
            }
        } finally {
            br.close();
        }
        BufferedImage imageLeft = ImageIO.read(new File("B:/Projects/Sasta Spotify/Project/src/Resources/images/a.png"));
        BufferedImage imageRIght = ImageIO.read(new File("B:/Projects/Sasta Spotify/Project/src/Resources/images/b.png"));
        ImageIcon iconPlay = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/play.png");
        ImageIcon iconPause = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/pause.png");
        ImageIcon iconLike = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/like.png");
        ImageIcon iconLogo = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/logo.jpg");
        ImageIcon iconPlay_ = new ImageIcon(iconPlay.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconPause_ = new ImageIcon(iconPause.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconLike_ = new ImageIcon(iconLike.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconLogo_ = new ImageIcon(iconLogo.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        //ImageIcon icon = new ImageIcon("C:\\Music player\\icon.jpg");
        JFrame frame = new JFrame("Music Player");
        frame.setSize(800, 600);
        JPanel upper = new JPanel();
        JPanel left = new ImagePanel(imageLeft);
        JPanel right = new ImagePanel(imageRIght);
        JPanel lower = new JPanel();

        
        input = new JTextField();
        input.setPreferredSize(new Dimension(225,37));
        input.setCaretColor(Color.BLACK);
        input.setBackground(Color.GRAY);
        input.setForeground(Color.WHITE);
        input.setFont(new Font("Consolas",Font.PLAIN,17));

        select = new JButton("Select");
        select.setFocusable(false);
        select.setBackground(Color.WHITE);
        select.setForeground(Color.BLACK);
        select.addActionListener(this);

        play = new JButton("Play",iconPlay_);
        play.setFocusable(false);
        play.setBackground(Color.WHITE);
        play.setForeground(Color.BLACK);
        play.addActionListener(this);

        stop = new JButton("Pause",iconPause_);
        stop.setFocusable(false);
        stop.setBackground(Color.WHITE);
        stop.setForeground(Color.BLACK);
        stop.addActionListener(this);

        restart = new JButton("Like",iconLike_);
        restart.setFocusable(false);
        restart.setBackground(Color.WHITE);
        restart.setForeground(Color.BLACK);
        restart.addActionListener(this);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        lower.setBounds(0,450,800,150);
        lower.setBackground(Color.WHITE);
        lower.setLayout(new FlowLayout());
        lower.add(select);
        lower.add(input);
        lower.add(play);
        lower.add(stop);
        lower.add(restart);
        lower.setBorder(border);

        upper.setBounds(0,0,800,50);
        upper.setBackground(Color.GREEN);
        upper.setBorder(border);
        myLabel = new JLabel("   No Song Playing");
        upper.add(myLabel);     
        JLabel labelLogo1 = new JLabel(iconLogo_);
        upper.add(labelLogo1);

        left.setBounds(0,50,300,400);
        left.setBackground(Color.WHITE);
        left.setBorder(border);
        JLabel labelLiked = new JLabel("Liked Songs");
        labelLiked.setFont(new Font("Serif", Font.BOLD, 40));
        labelLiked.setForeground(Color.WHITE);
        left.add(labelLiked,BorderLayout.NORTH);

        right.setBounds(300,50,500,400);
        right.setBackground(Color.WHITE);
        right.setBorder(border);
        JLabel labelLib = new JLabel("           All Songs        ");
        labelLib.setFont(new Font("Serif", Font.BOLD, 40));
        labelLib.setForeground(Color.WHITE);
        right.add(labelLib,BorderLayout.NORTH);
        

        JLabel[] labels = new JLabel[50];

        for(int i=0; i<count1; i++){
            labels[i]=new JLabel();
            labels[i].setFont(new Font("Serif", Font.BOLD, 20));
            labels[i].setForeground(Color.BLACK);
            labels[i].setText("          "+i+1+".         "+musics[i]);
            right.add(labels[i],BorderLayout.CENTER);
        }

        for(int i=0; i<count2; i++){
            labels[i]=new JLabel();
            labels[i].setFont(new Font("Serif", Font.BOLD, 20));
            labels[i].setForeground(Color.BLACK);
            labels[i].setText(i+1+".    "+liked[i]);
            left.add(labels[i],BorderLayout.CENTER);
        }

        frame.setLayout(null);
        frame.add(upper);
        frame.add(left);
        frame.add(right);
        frame.add(lower);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    int flag=0;
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==select){

            music_name = input.getText();
            try {
                if(flag==1)
                {
                    try {
                        play(3," ");
                        myLabel.setText("Selected : "+music_name);
                    } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
                play(1,music_name);
                flag = 1;
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==play) {
            try {
                play(2,"");
                myLabel.setText("Playing : "+music_name);
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==stop)
        {
            try {
                play(3," ");
                myLabel.setText("Paused : "+music_name);
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==restart)
        {
            try {
                play(4,"");
                myLabel.setText("Liked : "+music_name);
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    Clip clip = null;

    void play(int number,String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        if(number==1)
        {
            File file = new File("B:\\Projects\\Sasta Spotify\\Project\\src\\Resources\\"+name+".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        if(number==2)
            clip.start();

        if(number==3)
            clip.stop();

        if(number==4)
            clip.setMicrosecondPosition(0);
    }
}

