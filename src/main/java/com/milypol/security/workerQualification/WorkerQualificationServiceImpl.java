package com.milypol.security.workerQualification;

import org.springframework.stereotype.Service;

@Service
public class WorkerQualificationServiceImpl implements WorkerQualificationService{
    private final WorkerQualificationRepo workerQualificationRepo;

    public WorkerQualificationServiceImpl(WorkerQualificationRepo workerQualificationRepo) {
        this.workerQualificationRepo = workerQualificationRepo;
    }

    @Override
    public WorkerQualification getWorkerQualificationById(Integer id) {
        return workerQualificationRepo.findById(id).orElseThrow(() -> new RuntimeException("WorkerQualification not found"));
    }

    @Override
    public WorkerQualification saveWorkerQualification(WorkerQualification workerQualification) {
        return workerQualificationRepo.save(workerQualification);
    }

    @Override
    public void deleteWorkerQualification(Integer id) {
        workerQualificationRepo.deleteById(id);
    }
}
