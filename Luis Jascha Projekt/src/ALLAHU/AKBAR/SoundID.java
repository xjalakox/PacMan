package ALLAHU.AKBAR;

public enum SoundID {
	//template		(	id,	name,			vol),
	
	//Music
	allah			(	1,	"allah",		1);
	
	
	
	private int id;
	private String prefix = "res/";
	private String suffix = ".wav";
	private String name;
	// musicvol // effectvol // mastervol//
	private int[] volList = { 0, 0, 0};
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
