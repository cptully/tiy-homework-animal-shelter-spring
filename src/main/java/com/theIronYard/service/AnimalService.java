package com.theIronYard.service;

import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.TypeRepository;
import com.theIronYard.repository.NoteRepository;

/**
 * Created by chris on 8/12/16.
 */
public class AnimalService {
    // properties
    private AnimalRepository animalRepository;
    private BreedRepository breedRepository;
    private TypeRepository typeRepository;
    private NoteRepository noteRepository;

    /**
     * The AnimalService class constructor requires an AnimalRepository object
     *
     * @param animalRepository an AnimalRepository object
     */
    public AnimalService(AnimalRepository animalRepository,
                         TypeRepository typeRepository,
                         BreedRepository breedRepository,
                         NoteRepository noteRepository) {
        this.animalRepository = animalRepository;
        this.breedRepository = breedRepository;
        this.typeRepository = typeRepository;
        this.noteRepository = noteRepository;
    }


/*    public ArrayList<Type> getValidAnimalTypes() throws SQLException {
        ResultSet resultSet = animalTypeRepository.getAnimalTypes();

        ArrayList<Type> types = new ArrayList<>();
        while (resultSet.next()) {
            types.add(new Type(resultSet.getInt("typeid"), resultSet.getString("typename")));
        }

        return types;
    }

    public ArrayList<Breed> getValidAnimalBreeds() throws SQLException {
        ResultSet resultSet = animalBreedRepository.getBreeds();

        ArrayList<Breed> breeds = new ArrayList<>();
        while (resultSet.next()) {
            breeds.add(new Breed(
                    resultSet.getInt("breedid"),
                    resultSet.getString("breed"),
                    resultSet.getInt("typeid")));
        }
        return breeds;
    }

    *//**
     * listAnimals
     *
     * @return ArrayList&lt;String&gt; of string representations of animals
     *//*
    public ArrayList<Animal> listAnimals() throws SQLException {
        ResultSet resultSet = animalRepository.list();

        return buildAnimalList(resultSet);
    }

    public ArrayList<Type> listTypes() throws SQLException {
        ResultSet resultSet = animalTypeRepository.getAnimalTypes();

        ArrayList<Type> types = new ArrayList<>();
        while (resultSet.next()) {
            Type type = new Type(resultSet.getInt("typeid"), resultSet.getString("typename"));
            types.add(type);
        }

        return types;
    }

    public ArrayList<Note> listNotes(int id) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        ResultSet animalNotes = noteRepository.getNotes(id);
        while (animalNotes.next()){
            Note note = new Note(animalNotes.getInt("noteid"),
                    animalNotes.getString("note"),
                    animalNotes.getTimestamp("date").toLocalDateTime());
            notes.add(note);
        }
        return notes;
    }

    public void addNote(int animalId, String note) throws SQLException {
        noteRepository.addNote(animalId, note);
    }

    *//**
     * getAnimal gets the animal object for the submitted integer index
     * @param id - the object to be returned
     * @return an entity object
     *//*
    public Animal getAnimal(int id) throws SQLException {
        Animal newAnimal = new Animal();

        ResultSet resultSet = animalRepository.getAnimal(id);

        while (resultSet.next()) {
            newAnimal.setId(resultSet.getInt("id"));
            newAnimal.setName(resultSet.getString("name"));
            Type newType = new Type(
                    resultSet.getInt("typeid"),
                    resultSet.getString("typename"));
            newAnimal.setType(newType);
            Breed newBreed = new Breed(
                    resultSet.getInt("breedid"),
                    resultSet.getString("breed"),
                    resultSet.getInt("typeid"));
            newAnimal.setBreed(newBreed);
            newAnimal.setColor(resultSet.getString("color"));
            newAnimal.setDescription(resultSet.getString("description"));
        }

        ArrayList<Note> notes = listNotes(id);
        newAnimal.setNotes(notes);
        return newAnimal;
    }

    *//**
     * addAnimal adds the supplied entity to the AnimalRepository
     *
     * @param newAnimal entity to be added
     *//*
    public void addAnimal(Animal newAnimal) throws SQLException {
        animalRepository.addAnimal(newAnimal);
        ResultSet resultSet =  animalTypeRepository.getAnimalTypes();

    }

    *//**
     * editAnimal passes the edited animal to AnimalRepository
     *
     * @param animal the edited animal
     *//*
    public void editAnimal(Animal animal) throws SQLException {
        animalRepository.editAnimal(animal);
    }

    *//**
     * deleteAnimal removes the selected animal from the repository
     *
     * @param id the zero based index of the animal to deleteAnimal
     *//*
    public void deleteAnimal(int id) throws SQLException {
        animalRepository.removeAnimal(id);}

    public boolean contains(Animal animal) throws SQLException { return animalRepository.containsAnimal(animal.getId()); }

    public boolean contains(int id) throws SQLException {
        return animalRepository.containsAnimal(id);
    }

    public void deleteNote(int animalId, int noteId) throws SQLException {
        noteRepository.removeNote(animalId, noteId);
    }

    public int size() throws SQLException {return animalRepository.size();}

    public void addBreed(Breed breed) throws SQLException {
        animalBreedRepository.addBreed(breed);
    }

    public int deleteBreed(int breedId) throws SQLException {
        return animalBreedRepository.deleteBreed(breedId);
    }

    public ArrayList<Breed> listBreeds() throws SQLException {
        ArrayList<Breed> breeds = new ArrayList<>();
        ResultSet resultSet = animalBreedRepository.getBreeds();
        while (resultSet.next()){
            Breed breed = new Breed(resultSet.getInt("breedid"),
                                                resultSet.getString("breed"),
                                                resultSet.getInt("typeid"));
            breeds.add(breed);
        }
        return breeds;
    }

    public Breed getBreed(int breedId) throws SQLException {
        ResultSet resultSet = animalBreedRepository.getBreed(breedId);
        resultSet.next();
        Breed animalBreed = new Breed(resultSet.getInt("breedid"),
                                                  resultSet.getString("breed"),
                                                  resultSet.getInt("typeid"));
        return animalBreed;
    }

    public void editBreed(Breed animalBreed) throws SQLException {
        animalBreedRepository.editBreed(animalBreed);
    }

    public void addType(Type animalType) throws SQLException {
        animalTypeRepository.addType(animalType);
    }

    public Type getType(int typeId) throws SQLException {
        ResultSet resultSet = animalTypeRepository.getType(typeId);

        resultSet.next();
        return new Type(
                resultSet.getInt("typeid"),
                resultSet.getString("typename")
        );
    }

    public void editType(Type animalType) throws SQLException{
        animalTypeRepository.editType(animalType);
    }

    public int deleteType(int typeId) throws SQLException {
        return animalTypeRepository.deleteType(typeId);
    }

    public ArrayList<Animal> searchByName(String name) throws SQLException {
        ResultSet resultSet = animalRepository.searchByName(name);
        return buildAnimalList(resultSet);
    }

    public ArrayList<Animal> searchByType(int typeId) throws SQLException {
        ResultSet resultSet = animalRepository.searchByType(typeId);
        return buildAnimalList(resultSet);
    }

    public ArrayList<Animal> searchByBreed(int breedId) throws SQLException {
        ResultSet resultSet = animalRepository.searchByBreed(breedId);
        return buildAnimalList(resultSet);
    }

    private ArrayList<Animal> buildAnimalList(ResultSet resultSet) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        while (resultSet.next()) {

            Type newType = new Type(
                    resultSet.getInt("typeid"),
                    resultSet.getString("typename"));
            Breed newBreed = new Breed(
                    resultSet.getInt("breedid"),
                    resultSet.getString("breed"),
                    resultSet.getInt("typeid"));
            Animal newAnimal = new Animal(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    newType,
                    newBreed,
                    resultSet.getString("color"),
                    resultSet.getString("description"));
            ArrayList<Note> notes = listNotes(newAnimal.getId());
            newAnimal.setNotes(notes);
            animals.add(newAnimal);
        }

        return animals;
    }*/
}
