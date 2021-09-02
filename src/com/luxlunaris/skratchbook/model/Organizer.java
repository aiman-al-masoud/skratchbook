package com.luxlunaris.skratchbook.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.luxlunaris.skratchbook.interfaces.Metadata;
import com.luxlunaris.skratchbook.interfaces.TagListener;


/**
 * A wrapper for MetadataFile, that notifies any registered
 * TagListener(s) when a tag's value changes after 
 * setTag was invoked.
 * @author aiman
 *
 */
public abstract class Organizer implements Metadata{
	
	 /**
     * Associates to (potentially) each tag a list of listeners of that tag.
     */
    private HashMap<Tag, ArrayList<TagListener>> tagListeners;

    
    /**
     * Stores all of the tags managed by this Organizer.
     */
    Metadata metadata;
    
    
    public Organizer(String pathname) {
    	metadata = new MetadataFile(pathname);
    	tagListeners = new HashMap<>();
    }
    

	
	@Override
	public String getString(Tag tag) {
		return metadata.getString(tag);
	}

	@Override
	public int getInt(Tag tag) {
		return metadata.getInt(tag);
	}

	@Override
	public boolean getBoolean(Tag tag) {
		return metadata.getBoolean(tag);
	}


	@Override
	public double getFloat(Tag tag) {
		return metadata.getFloat(tag);
	}

	@Override
	public void setTag(Tag tag, String tagValue) {
		metadata.setTag(tag, tagValue);
		notifyListenersOfTag(tag);
	}
	
	/**
	 * Notify all of a Tag's registered listeners.
	 * @param tag
	 */
	private void notifyListenersOfTag(Tag tag){
        ArrayList<TagListener> listenersOfTag = tagListeners.get(tag);
        if(listenersOfTag!=null){
            for(TagListener listener : listenersOfTag){
                try {
                    listener.onTagUpdated(tag);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Registers a new listener interested in being notified when the
     * value of a tag changes.
     * @param tag
     * @param listener
     */
    public void addTagListener(Tag tag, TagListener listener){

        //get the list of listeners of a tag
        ArrayList<TagListener> listeners = tagListeners.get(tag);

        //if the tag doesn't have any listeners yet
        if(listeners==null){
            //create the list
            listeners =  new ArrayList<TagListener>();
            //add the list to the map
            tagListeners.put(tag,listeners);
        }

        //add the new listener to the list of listeners of a certain tag
        listeners.add(listener);
    }
	

	@Override
	public void removeTag(Tag tag) {
		metadata.removeTag(tag);
	}

	@Override
	public boolean create() {
		return metadata.create();
	}

	@Override
	public boolean delete() {
		return metadata.delete();
	}
	
	
	

}
