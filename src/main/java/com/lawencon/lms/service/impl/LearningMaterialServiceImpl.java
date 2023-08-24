package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.FileDao;
import com.lawencon.lms.dao.LearningDao;
import com.lawencon.lms.dao.LearningMaterialAttachmentDao;
import com.lawencon.lms.dao.LearningMaterialDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.attachmentmaterial.AttachmentMaterialResDto;
import com.lawencon.lms.dto.learningmaterial.LearningMaterialInsertReqDto;
import com.lawencon.lms.dto.learningmaterial.LearningMaterialResDto;
import com.lawencon.lms.model.AttachmentMaterial;
import com.lawencon.lms.model.File;
import com.lawencon.lms.model.Learning;
import com.lawencon.lms.model.LearningMaterial;
import com.lawencon.lms.service.LearningMaterialService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class LearningMaterialServiceImpl implements LearningMaterialService {

	private final LearningDao learningDao;
	private final FileDao fileDao;
	private final LearningMaterialDao learningMaterialDao;
	private final LearningMaterialAttachmentDao learningMaterialAttachmentDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public LearningMaterialServiceImpl(LearningDao learningDao, FileDao fileDao,
			LearningMaterialAttachmentDao learningMaterialAttachmentDao, LearningMaterialDao learningMaterialDao,
			PrincipalService principalService) {
		this.learningDao = learningDao;
		this.fileDao = fileDao;
		this.learningMaterialDao = learningMaterialDao;
		this.learningMaterialAttachmentDao = learningMaterialAttachmentDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(LearningMaterialInsertReqDto material) {
		InsertResDto result = new InsertResDto();

		final LearningMaterial newMaterial = new LearningMaterial();
		final String randomCode = generateID(5);
		newMaterial.setMaterialCode(randomCode);
		newMaterial.setMaterialName(material.getMaterialName());
		newMaterial.setMaterialText(material.getMaterialText());

		final Learning learning = learningDao.getById(material.getLearningId());
		newMaterial.setLearning(learning);
		newMaterial.setCreatedBy(principalService.getPrincipal());

		learningMaterialDao.insert(newMaterial);

		if (material.getFiles() != null) {

			for (int i = 0; i < material.getFiles().size(); i++) {
				File file = new File();
				file.setFile(material.getFiles().get(i).getFile());
				file.setFileExtension(material.getFiles().get(i).getFileExtension());
				file.setCreatedBy(principalService.getPrincipal());
				file = fileDao.insert(file);

				final AttachmentMaterial attachment = new AttachmentMaterial();
				attachment.setFile(file);
				attachment.setLearningMaterial(newMaterial);
				attachment.setCreatedBy(principalService.getPrincipal());
				learningMaterialAttachmentDao.insert(attachment);
			}

		}

		result.setId(newMaterial.getId());
		result.setMessage("New Material Has Been Added: " + newMaterial.getMaterialCode());
		return result;
	}

	@Override
	public List<LearningMaterialResDto> getByLearningId(Long learningid) {
		final List<LearningMaterialResDto> materials = new ArrayList<>();

		learningMaterialDao.getByLearningId(learningid).forEach(m -> {
			final LearningMaterialResDto material = new LearningMaterialResDto();
			material.setId(m.getId());
			material.setMaterialCode(m.getMaterialCode());
			material.setMaterialName(m.getMaterialName());
			material.setMaterialText(m.getMaterialText());

			materials.add(material);
		});

		return materials;
	}

	@Override
	public List<AttachmentMaterialResDto> getByMaterial(Long materialid) {
		final List<AttachmentMaterialResDto> attachments = new ArrayList<>();

		learningMaterialAttachmentDao.getByMaterial(materialid).forEach(a -> {
			final AttachmentMaterialResDto attachment = new AttachmentMaterialResDto();
			attachment.setFile(a.getFile().getFile());
			attachment.setFileExtension(a.getFile().getFileExtension());
			attachment.setFileId(a.getFile().getId());
			attachment.setMaterialName(a.getLearningMaterial().getMaterialName());
			attachments.add(attachment);
		});

		return attachments;
	}

	@Override
	public LearningMaterialResDto getById(Long id) {
		final LearningMaterial materi = learningMaterialDao.getById(id);
		
		final LearningMaterialResDto material = new LearningMaterialResDto();
		material.setId(materi.getId());
		material.setMaterialCode(materi.getMaterialCode());
		material.setMaterialName(materi.getMaterialName());
		material.setMaterialText(materi.getMaterialText());
		
		return material;
	}

}
