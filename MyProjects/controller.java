import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class controller implements ActionListener {

    private view view;
    private model model;
    private String selectedMusicName;

    public controller(view view, model model) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.view = view;
        this.model = model;

        view.updateLiked(model.getLiked(), model.getCount2());
        view.updateMusic(model.getMusics(), model.getCount1());

        view.getPlayButton().addActionListener(this);
        view.getPauseButton().addActionListener(this);
        view.getLikeButton().addActionListener(this);
        view.getSelectButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getSelectButton()) {
            selectedMusicName = view.getMusicName();
            try {
                model.select(selectedMusicName);
                view.updateMyLabel("Selected : "+selectedMusicName);
                System.out.println("Opened");
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();}
            
            
        } else if (e.getSource() == view.getPlayButton()) {
            try{
                model.play();
                view.updateMyLabel("Playing : "+selectedMusicName);
            }catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();}
            System.out.println("Playing");
            
        } else if (e.getSource() == view.getPauseButton()) {
            try{
                model.pause();
                view.updateMyLabel("Paused : "+selectedMusicName);
            }catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();}
            System.out.println("Paused");
        
        } else if (e.getSource() == view.getLikeButton()) {
            try {
                model.like(selectedMusicName);
                view.updateLiked(model.getLiked(), model.getCount2());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Liked");
        } else if (e.getSource() == view.getRefreshButton()) {
            try{
                view.updateLiked(model.getLiked(), model.getCount2());
                System.out.println("Refreshed");
            }catch (IOException ex){
                ex.printStackTrace();
            }
             
        }
    }
}
