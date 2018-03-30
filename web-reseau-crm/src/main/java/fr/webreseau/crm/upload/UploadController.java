package fr.webreseau.crm.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.webreseau.crm.service.IServiceSessionUser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class UploadController {

	@Autowired
	private IServiceSessionUser serviceSessionUser;
	
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:/DownloadTest//";

    @GetMapping("/files")
    public String pageFiles(Model model) {
    	serviceSessionUser.getSessionUser(model);
    	model.addAttribute("listFiles",listFilesAndFolders(UPLOADED_FOLDER));
		return "files/files";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(Model model) {
    	serviceSessionUser.getSessionUser(model);
        return "files/uploadStatus";
    }
    
    public ArrayList<String> listFilesAndFolders(String directoryName){
        File directory = new File(directoryName);
        ArrayList<String> listFile = new ArrayList<String>();
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
        	listFile.add(file.getName());
        }
        return listFile;
    }

}