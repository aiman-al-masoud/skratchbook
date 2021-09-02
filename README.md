# skratchbook

Adding persistent settings is a great way to enhace an app's customizability. Most often this is done through the use of so called "config" files, that is, bluntly: files that are managed as a persistent dictionary. But coding the part that manages access to disk can get tedious, especially if you're working on many projects at once. 

Skratchbook adds a much needed layer of abstraction to the whole process, making it so that you can finally focus on the business logic of your program! The library provides an easy way to store and retrieve snippets of data from disk, as well as a flexible means of notifyng objects of interest when a parameter's value changes.


<img src="https://github.com/aiman-al-masoud/skratchbook/blob/main/res/skratchbook.png"></img>

## Getting Started:

To use this library to manage persistent settings, take the following steps:

* Extend the 'Organizer' abstract class. For instance: 


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








