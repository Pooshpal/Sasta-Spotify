
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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


public class view extends JFrame {
    private final JButton select;
    private final JButton play;
    private final JButton pause;
    private final JButton like;
    private final JButton refresh;
    private final JTextField input;
    private final JLabel labelLogo;
    private final JLabel labelAll;
    private final JLabel blank;
    private JLabel labelLiked;
    private JLabel myLabel;
    private final JPanel lower;
    private final JPanel upper;
    private final JPanel left;
    private final JPanel right;
    private final JFrame frame;

    public view() throws IOException {

        final BufferedImage imageLeft = ImageIO.read(new File("B:/Projects/Sasta Spotify/Project/src/Resources/images/a.png"));
        final BufferedImage imageRIght = ImageIO.read(new File("B:/Projects/Sasta Spotify/Project/src/Resources/images/b.png"));
        final ImageIcon iconPlay = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/play.png");
        final ImageIcon iconPause = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/pause.png");
        final ImageIcon iconLike = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/like.png");
        final ImageIcon iconRefresh = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/refresh.jpg");
        final ImageIcon iconLogo = new ImageIcon("B:/Projects/Sasta Spotify/Project/src/Resources/images/logo.jpg");

        final ImageIcon iconPlay_ = new ImageIcon(iconPlay.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        final ImageIcon iconPause_ = new ImageIcon(iconPause.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        final ImageIcon iconLike_ = new ImageIcon(iconLike.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        final ImageIcon iconRefresh_ = new ImageIcon(iconRefresh.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        final ImageIcon iconLogo_ = new ImageIcon(iconLogo.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));

        frame = new JFrame("Music Player");
        upper = new JPanel();
        left = new ImagePanel(imageLeft);
        right = new ImagePanel(imageRIght);
        lower = new JPanel();

        labelLogo = new JLabel(iconLogo_);
        myLabel = new JLabel("   No Song Playing");
        labelLiked = new JLabel("Liked Songs");
        labelLiked.setFont(new Font("Serif", Font.BOLD, 40));
        labelLiked.setForeground(Color.WHITE);
        labelAll = new JLabel("           All Songs        ");
        labelAll.setFont(new Font("Serif", Font.BOLD, 40));
        labelAll.setForeground(Color.WHITE);
        blank = new JLabel("");
        
        select = new JButton("Select");
        input = new JTextField();
        play = new JButton("Play", iconPlay_);
        pause = new JButton("Pause", iconPause_);
        like = new JButton("Like", iconLike_);
        refresh = new JButton("Refresh", iconRefresh_);

        input.setPreferredSize(new Dimension(225, 37));
        input.setCaretColor(Color.BLACK);
        input.setBackground(Color.GRAY);
        input.setForeground(Color.WHITE);
        input.setFont(new Font("Consolas", Font.PLAIN, 17));

        select.setFocusable(false);
        select.setBackground(Color.WHITE);
        select.setForeground(Color.BLACK);

        play.setFocusable(false);
        play.setBackground(Color.WHITE);
        play.setForeground(Color.BLACK);

        pause.setFocusable(false);
        pause.setBackground(Color.WHITE);
        pause.setForeground(Color.BLACK);

        like.setFocusable(false);
        like.setBackground(Color.WHITE);
        like.setForeground(Color.BLACK);

        refresh.setFocusable(false);
        refresh.setBackground(Color.WHITE);
        refresh.setForeground(Color.BLACK);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        lower.setBounds(0, 450, 800, 150);
        lower.setBackground(Color.WHITE);
        lower.setLayout(new FlowLayout());
        lower.add(select);
        lower.add(input);
        lower.add(play);
        lower.add(pause);
        lower.add(like);
        lower.add(refresh);
        lower.setBorder(border);

        upper.setBounds(0, 0, 800, 50);
        upper.setBackground(Color.GREEN);
        upper.setBorder(border);
        upper.add(myLabel);
        upper.add(labelLogo);

        left.setBounds(0, 50, 300, 400);
        left.setBackground(Color.WHITE);
        left.setBorder(border);
        left.add(labelLiked,BorderLayout.NORTH);

        right.setBounds(300, 50, 500, 400);
        right.setBackground(Color.WHITE);
        right.setBorder(border);
        right.add(labelAll,BorderLayout.NORTH);
        right.add(blank);

        frame.setLayout(null);
        frame.add(upper);
        frame.add(left);
        frame.add(right);
        frame.add(lower);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void createAndAddLabels(JPanel panel, String[] items, int count) {
        JLabel[] labels = new JLabel[50];
        panel.add(blank,BorderLayout.CENTER);
        for (int i = 0; i < count; i++) {
            labels[i] = new JLabel();
            labels[i].setFont(new Font("Serif", Font.BOLD, 20));
            labels[i].setForeground(Color.BLACK);
            labels[i].setText("    " + i + ".     " + items[i]);
            labels[i].setPreferredSize(new Dimension(300,37));
            panel.add(labels[i], BorderLayout.CENTER);
        }
    }

    private void clearLabels(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setText("");
            }
        }
    }

    public void updateLiked(String[] liked, int count) throws IOException{
        clearLiked();
        createAndAddLabels(left, liked, count);
    }

    public void updateMusic(String[] music, int count) {
        createAndAddLabels(right, music, count);
    }

    public void clearLiked() {
        System.out.println("Cleared Liked");
        clearLabels(left);
        labelLiked = new JLabel("Liked Songs");
        labelLiked.setFont(new Font("Serif", Font.BOLD, 40));
        labelLiked.setForeground(Color.WHITE);
        left.add(labelLiked,BorderLayout.NORTH);
    }

    public JButton getSelectButton() {
        return select;
    }

    public String getMusicName() {
        return input.getText();
    }

    public JButton getPlayButton() {
        return play;
    }
    
    public JButton getPauseButton() {
        return pause;
    }
    
    public JButton getLikeButton() {
        return like;
    }

    public JButton getRefreshButton() {
        return refresh;
    }

    public void updateMyLabel(String t){
        myLabel.setText(t);
    }
}
