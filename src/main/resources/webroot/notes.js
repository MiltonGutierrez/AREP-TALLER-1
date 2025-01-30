
const notes = (() => {

    const api = apiClient;

    const getNote = async () => {
        try {
            //notes = await api.getNotes();
        } catch (error) {
            
        }
    };

    const addNote = async (title, group, content) => {
        try {
            console.log(title, group, content);
            var response = await api.addNote(title, group, content);
        } catch (error) {

        }

     };

    return {
        getNote,
        addNote
    }
})();
