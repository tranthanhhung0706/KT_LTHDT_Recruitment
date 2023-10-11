package com.tttn.ptithcm.util;

import java.util.ArrayList;
import java.util.List;

import com.tttn.ptithcm.entity.common.PositionCategory;

public class PositionCategoryUtil {

	
	public static List<PositionCategory> getTopPositionCategory(List<PositionCategory> allPositionCategories){
		List<PositionCategory> topList = new ArrayList<PositionCategory>();
		for(PositionCategory positionCategory : allPositionCategories)
		{
			if(positionCategory.getParentId() == null || positionCategory.getParentId().getId() == null)
			{
				topList.add(positionCategory);
			}
		}
		
		return topList;
	}
	

	public static List<PositionCategory> getSecondCategory(List<PositionCategory> allPositionCategories){
		List<PositionCategory> secondList = new ArrayList<PositionCategory>();
		for(PositionCategory positionCategory : allPositionCategories)
		{
			if(positionCategory.getParentId() != null && positionCategory.getParentId().getId() != null)
			{
				if(positionCategory.getParentId().get_parentId() == null)
				{
					secondList.add(positionCategory);
				}
			}
		}
		
		return secondList;
	}
	
	
	public static List<PositionCategory> getThirdCategory(List<PositionCategory> allPositionCategories){
		List<PositionCategory> thirdList = new ArrayList<PositionCategory>();
		for(PositionCategory positionCategory : allPositionCategories)
		{
			if(positionCategory.getParentId() != null && positionCategory.getParentId().getId() != null)
			{
				if(positionCategory.getParentId().getParentId() != null && positionCategory.getParentId().getParentId().getId() != null)
				{
					if(positionCategory.getParentId().getParentId().get_parentId() == null)
					{
						thirdList.add(positionCategory);
					}
				}
			}
		}
		
		return thirdList;
	}
	

	public static List<PositionCategory> getQuickIndexCategory(List<PositionCategory> allPositionCategories)
	{
		List<PositionCategory> quickIndexList = new ArrayList<PositionCategory>();
		//Nhận danh mục thứ hai
		List<PositionCategory> secondList = PositionCategoryUtil.getSecondCategory(allPositionCategories);
		//Nhận ba loại
		List<PositionCategory> thirdList = PositionCategoryUtil.getThirdCategory(allPositionCategories);
		for(PositionCategory secondCategory : secondList)
		{
			for(PositionCategory thirdCategory : thirdList)
			{
				if(thirdCategory.get_parentId() == secondCategory.getId())
				{
					quickIndexList.add(thirdCategory);
					break;
				}
			}
		}
		return quickIndexList;
	}
	
}
