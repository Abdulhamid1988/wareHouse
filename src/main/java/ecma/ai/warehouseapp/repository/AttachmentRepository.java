package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
