# skratchbook

Adding persistent settings is a great way to enhance your app's customizability and user-friendliness. Most often this is done through the use of so called "config" files, that is, bluntly: files that are managed as dictionaries. But coding the part that manages that kind of access to a file can get quite tedious, especially if you're working on multiple projects at once. 

Skratchbook adds a much needed layer of abstraction to the whole process, making it so that you can finally focus on the business logic of your program! The library provides an easy way to store and retrieve snippets of data from disk, as well as a flexible means of notifyng objects of interest the instant a parameter's value changes.


<img src="https://github.com/aiman-al-masoud/skratchbook/blob/main/res/skratchbook.png"></img>

## Getting Started:

To import this library, download the <a href="https://github.com/aiman-al-masoud/skratchbook/releases">latest stable release</a>, unzip "skratchbook.zip", and add the jar file "skratchbook.jar" to your project's build-path.

## Managing Settings:

To build an app-wide settings-manager for your project, you can do the following:

Extend the 'Organizer' abstract class. For instance: 

```
public class Settings extends Organizer {

	/**
	*I suggest making this class a singleton. 
	*/
	private static Settings instance;
	
	/**
	* The path to the file that's gonna persistently store your tag values.
	*/
	private static final String PATH = "test_files/settings";

	/**
	*Define your tags and their default value here in string form:
	*/
	public static final Tag EXAMPLE_NUMBER = new Tag("example_number", 1000+"");
	public static final Tag EXAMPLE_COLOR = new Tag("example_color", 1232+"");

	
	private Settings() {
		super(PATH);
	}
	
	public static Settings instance() {
		return instance!=null? instance : (instance=new Settings() );
	}
  
  }
```

Now you can call Settings, from anywhere in your project, to store or retrive the value of a tag!


```
//setting the value of a tag
Settings.instance().setTag(Settings.EXAMPLE_NUMBER, 1001+"")

//...

//retrieving the value of a tag
int myNum = Settings.instance().getInt(Settings.EXAMPLE_NUMBER);
```

If you need a class to be notified instantly whenever a tag's value is modified, you can implement the interface 'TagListener', like so:


```
public class SomeGUI implements TagListener{

        /**
	* This class needs to be notified whenever a tag that
	* stores a color's rgb value changes. 
	*/
	public SomeGUI(){
	   Settings.instance().addTagListener(Settings.EXAMPLE_COLOR, this);
	}
	
	
        @Override
	public void onTagUpdated(Tag tag) {
	
	        if(tag==Settings.EXAMPLE_COLOR){
		    //get the new value of the modified tag from Settings
	            int newColor = Settings.instance().getInt(tag);
		    //display the change in color
		    setColor(newColor);
                }	        
	}
	
	
	private void setColor(int newColor){
	//...
	}
}


```

### For a real working example, check out <a href="https://github.com/aiman-al-masoud/skratchbook/tree/main/src/com/luxlunaris/skratchbook/tests/color_picker">this simple Swing color-picker</a>.


If you've downloaded the jar, you can also try the color-picker demo out, by running:

```
java -jar skratchbook color-picker
```


