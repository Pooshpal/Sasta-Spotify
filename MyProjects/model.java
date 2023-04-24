
import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class model {
    private List<String> musics;
    private List<String> liked;
    private int count1;
    private int count2;
    private Clip clip = null;

    public model() throws IOException {
        this.musics = new ArrayList<>();
        this.liked = new ArrayList<>();
        this.count1 = 0;
        this.count2 = 0;
        loadMusic();
        //loadLiked();
    }

    private void loadMusic() throws IOException {
        String fileName = "B:/Projects/Sasta Spotify/Project/src/Resources/musics.txt";
        loadFileToList(fileName, musics);
        count1 = musics.size();
    }

    private void loadLiked() throws IOException {
        liked.clear();
        String fileName = "B:/Projects/Sasta Spotify/Project/src/Resources/liked.txt";
        loadFileToList(fileName, liked);
        count2 = liked.size();
    }

    private void loadFileToList(String fileName, List<String> list) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            String line = br.readLine();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
    
    public String[] getMusics() {
        return musics.toArray(new String[musics.size()]);
    }

    public String[] getLiked() {
        try{loadLiked();}catch( IOException ex){ ex.printStackTrace();}
        System.out.println(liked);
        return liked.toArray(new String[liked.size()]);
    }

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }

    public void select(String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("B:\\Projects\\Sasta Spotify\\Project\\src\\Resources\\"+name+".wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    public void play()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.start();
        
    }

    public void pause()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
    }

    public void like(String name) throws IOException {
        String fileName = "B:\\Projects\\Sasta Spotify\\Project\\src\\Resources\\liked.txt";
        String nameToAppend = name;

        if (!liked.contains(nameToAppend)) {
            liked.add(nameToAppend);
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(nameToAppend + "\n");
            writer.close();
        }
    }
}
