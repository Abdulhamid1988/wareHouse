package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Attachment;
import ecma.ai.warehouseapp.entity.AttachmentContent;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.AttachmentContentRepository;
import ecma.ai.warehouseapp.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {


    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();

            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);

            Attachment savedAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());

            attachmentContentRepository.save(attachmentContent);

            return new ApiResponse("file saved successfully", true);
        }
        return new ApiResponse("something went wrong",false);
    }


    public ApiResponse download(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()){
                AttachmentContent attachmentContent = contentOptional.get();

                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getFileOriginalName() + "\"");
                response.setContentType(attachment.getContentType());


                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());

                    return new ApiResponse("download ",true);
            }
        }
        return new ApiResponse("error occured please try again later",false);
    }



}
