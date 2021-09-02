# skratchbook
A simple persistence library.

<img src="https://github.com/aiman-al-masoud/skratchbook/blob/main/res/skratchbook.png"></img>

## Getting Started:

To use this library to manage persistent settings, take the following steps:

* Extend the 'Organizer' abstract class: 
(I suggest making it a singleton)

```
public class Settings extends Organizer {
	
	private static final String PATH = "test_files/settings";
	private static Settings instance;
	
	public static final Tag TAG_COLOR_BG = new Tag("bg_color", Color.yellow.getRGB()+"");
	
	private Settings() {
		super(PATH);
	}
	
	public static Settings instance() {
		return instance!=null? instance : (instance=new Settings() );
	}
  
  }
```

