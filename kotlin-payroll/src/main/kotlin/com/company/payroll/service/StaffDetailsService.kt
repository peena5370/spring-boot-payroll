package com.company.payroll.service

import com.company.payroll.model.StaffDetails
import com.github.pagehelper.PageInfo
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

interface StaffDetailsService {
  /**
   *
   * @param deptNo
   * @return
   */
  fun countActiveStaff(deptNo: Int): Int

  /**
   *
   * @param staffId
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun deleteStaffDetails(staffId: Int): Int

  /**
   *
   * @param staffId
   * @return
   */
  fun findByStaffId(staffId: Int): StaffDetails?

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listStaffDetails(page: Int, offset: Int): PageInfo<StaffDetails>

  /**
   *
   * @param staffDetails
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun addStaffDetails(staffDetails: StaffDetails): StaffDetails

  /**
   *
   * @param staffDetails
   * @return
   */
  fun updateStaffDetails(staffDetails: StaffDetails): StaffDetails
}