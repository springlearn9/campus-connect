package com.campusconnect.service;

import com.campusconnect.model.Notice;
import com.campusconnect.model.User;
import com.campusconnect.repository.NoticeRepository;
import com.campusconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all notices
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // Get notice by ID
    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }

    // Create new notice
    public Notice createNotice(Notice notice, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            notice.setPostedBy(user.get());
            return noticeRepository.save(notice);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }

    // Update notice
    public Notice updateNotice(Long id, Notice noticeDetails, Long userId) {
        Optional<Notice> noticeOptional = noticeRepository.findById(id);
        if (noticeOptional.isPresent()) {
            Notice notice = noticeOptional.get();
            
            // Check if user is authorized to update (owner or admin)
            if (!notice.getPostedBy().getId().equals(userId)) {
                Optional<User> user = userRepository.findById(userId);
                if (!user.isPresent() || !"ADMIN".equals(user.get().getRole())) {
                    throw new RuntimeException("Unauthorized to update this notice");
                }
            }
            
            notice.setTitle(noticeDetails.getTitle());
            notice.setDescription(noticeDetails.getDescription());
            notice.setPriority(noticeDetails.getPriority());
            notice.setCategory(noticeDetails.getCategory());
            notice.setValidUntil(noticeDetails.getValidUntil());
            notice.setStatus(noticeDetails.getStatus());
            notice.setAttachmentUrl(noticeDetails.getAttachmentUrl());
            
            return noticeRepository.save(notice);
        }
        throw new RuntimeException("Notice not found with id: " + id);
    }

    // Delete notice
    public void deleteNotice(Long id, Long userId) {
        Optional<Notice> noticeOptional = noticeRepository.findById(id);
        if (noticeOptional.isPresent()) {
            Notice notice = noticeOptional.get();
            
            // Check if user is authorized to delete (owner or admin)
            if (!notice.getPostedBy().getId().equals(userId)) {
                Optional<User> user = userRepository.findById(userId);
                if (!user.isPresent() || !"ADMIN".equals(user.get().getRole())) {
                    throw new RuntimeException("Unauthorized to delete this notice");
                }
            }
            
            noticeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notice not found with id: " + id);
        }
    }

    // Get active notices
    public List<Notice> getActiveNotices() {
        return noticeRepository.findActiveNotices(LocalDateTime.now());
    }

    // Get notices by status
    public List<Notice> getNoticesByStatus(String status) {
        return noticeRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    // Get notices by category
    public List<Notice> getNoticesByCategory(String category) {
        return noticeRepository.findByCategoryOrderByCreatedAtDesc(category);
    }

    // Get notices by priority
    public List<Notice> getNoticesByPriority(String priority) {
        return noticeRepository.findByPriorityOrderByCreatedAtDesc(priority);
    }

    // Search notices by title
    public List<Notice> searchNoticesByTitle(String keyword) {
        return noticeRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(keyword);
    }

    // Get notices by user
    public List<Notice> getNoticesByUser(Long userId) {
        return noticeRepository.findByPostedByIdOrderByCreatedAtDesc(userId);
    }

    // Get high priority notices
    public List<Notice> getHighPriorityNotices() {
        return noticeRepository.findHighPriorityActiveNotices(LocalDateTime.now());
    }
}