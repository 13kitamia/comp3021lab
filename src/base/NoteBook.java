package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;

	public NoteBook(){
		this.folders = new ArrayList<Folder>();
	}

	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);

		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);

		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);

		return insertNote(folderName, note);
	}

	public ArrayList<Folder> getFolders(){
		return this.folders;
	}

	public boolean insertNote(String folderName, Note note){
		for (int i = 0; i < this.folders.size(); ++i){
			if(folders.get(i).equals(folderName)){
				for(int j = 0; j < folders.get(i).getNotes().size(); ++j){
					if(folders.get(i).getNotes().get(j).equals(note)){
						System.out.println("Creating note " + note.getTitle() +
								" under folder " + folderName + " failed");
						return false;
					}
				}
				folders.get(i).addNote(note);
				return true;
			}
		}
		folders.add(new Folder(folderName));
		folders.get(folders.size() - 1).addNote(note);
		return true;
	}

	public void sortFolders(){
		for(int i = 0; i < folders.size(); ++i){
			folders.get(i).sortNotes();
		}
		Collections.sort(folders);
	}

	public List<Note> searchNotes(String keywords){
		List<Note> results = new ArrayList<Note>();
		for (int i = 0; i < folders.size(); ++i){
			results.addAll(folders.get(i).searchNotes(keywords));
		}

		return new ArrayList<Note>(new HashSet<Note>(results));
	}
}
