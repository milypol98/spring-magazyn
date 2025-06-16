package com.milypol.security.workerQualification;

public interface WorkerQualificationService {
    WorkerQualification getWorkerQualificationById(Integer id);
    WorkerQualification saveWorkerQualification(WorkerQualification workerQualification);
    void deleteWorkerQualification(Integer id);
}
