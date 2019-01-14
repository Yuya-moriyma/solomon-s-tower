package model;

public class StoryModel {
    private int storyKey;

    public StoryModel(int storyKey) {
        this.setStoryKey(storyKey);
    }

    public int getStoryKey() {
        return storyKey;
    }

    public void setStoryKey(int storyKey) {
        this.storyKey = storyKey;
    }
}
