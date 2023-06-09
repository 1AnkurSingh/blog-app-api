package blogappapi.Service;

import blogappapi.exception.BadApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService{

    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {



        // File name
        String originalFilename =file.getOriginalFilename();

        logger.info("FileName :{}",originalFilename);

        //random name generate file
        String fileName= UUID.randomUUID().toString();
        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));

        // FUll path
        String fileNameWithExtension =fileName+extension;
        String fullPathWithFileName=path+File.separator+fileNameWithExtension;

        if(extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg")|| extension.equalsIgnoreCase(".jpeg")) {

            //Create folder if not created
            File f=new File(path);
            if(!f.exists()){
                f.mkdir();
            }

            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));

            return fileNameWithExtension;

        }else
        {
            throw new BadApiRequest("File With This "+extension +" no allowed!!");
        }

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
