package main;

public enum SoundID {
	//template				(	id,	name,				vol),
	
	//Music
	intermission			(	0,	"intermission",		1),
	jump					(  	1,  "jump"	,           1),
	powerup					(	2,	"powerup",			1),
	intro					(	3,	"intro",			1),
	chomp					(	4,	"chomp",			1);
			
	
	
	
	private int id;
	private String prefix = "/sound/";
	private String suffix = ".wav";
	private String name;
	// musicvol // effectvol // mastervol//
	private int[] volList = { -20,-20,-20};
	private int vol;
	
	SoundID(int id, String name, int vol) {
		this.id =id;
		this.name = name;
		
		
		for(int i = 0; i < volList.length; i++) {
			if(vol == i) {
				this.vol = volList[i];
			}
		}
	}
	
	public int getID() {
		return this.id;
	}
	public  String getPath() {
		return this.prefix + this.name + this.suffix;
	}
	public int getVolume() {
		return this.vol;
	}
}
