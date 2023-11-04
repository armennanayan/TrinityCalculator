package edu.trinity.got;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberDAO implements MemberDAO {
    private final Collection<Member> allMembers =
            MemberDB.getInstance().getAllMembers();

   @Override
    public Optional<Member> findById(Long id) {
        return allMembers.stream().filter(member -> member.id().equals(id)).findFirst();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return allMembers.stream().filter(member -> member.name().equals(name)).findFirst();
    }

    @Override
    public List<Member> findAllByHouse(House house) {
        return allMembers.stream().filter(member -> member.house().equals(house)).toList();
    }

    @Override
    public Collection<Member> getAll() {
        return allMembers.stream().toList();
    }

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Override
    public List<Member> startWithSandSortAlphabetically() {
        return allMembers.stream().filter(member -> member.name().startsWith("S")).sorted().toList();
    }

    /**
     * Final all Lannisters and sort them by name
     */
    @Override
    public List<Member> lannisters_alphabeticallyByName() {
        return allMembers.stream().filter(member -> member.house() == House.LANNISTER).sorted(Comparator.comparing(Member::name)).toList();
    }

    /**
     * Find all members whose salary is less than the given value and sort by house
     */
    @Override
    public List<Member> salaryLessThanAndSortByHouse(double max) {
        return allMembers.stream().filter(member -> member.salary() < max).sorted(Comparator.comparing(Member::house)).toList();
    }

    /**
     * Sort members by House, then by name
     */
    @Override
    public List<Member> sortByHouseNameThenSortByNameDesc() {
        return allMembers.stream().sorted(Comparator.comparing(Member::house)).sorted(Comparator.comparing(Member::house)).toList();
    }

    /**
     * Sort the members of a given House by birthdate
     */
    @Override
    public List<Member> houseByDob(House house) {
        return allMembers.stream().filter(member -> member.house() == house).sorted(Comparator.comparing(Member::dob)).toList();
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Override
    public List<Member> kingsByNameDesc() {
        return allMembers.stream().filter(member -> member.title() == Title.KING).sorted(Comparator.comparing(Member::name).reversed()).toList();
    }

    /**
     * Find the average salary of all the members
     */
    @Override
    public double averageSalary() {
        return allMembers.stream().mapToDouble(Member::salary).average().orElse(0.0);
    }

    /**
     * Get the names of a given house, sorted in natural order
     * (note sort by _names_, not members)
     */
    @Override
    public List<String> namesSorted(House house) {
        return allMembers.stream().filter(member -> member.house() == house).sorted(Comparator.comparing(Member::name)).map(Member::name).toList();
    }

    /**
     * Are any of the salaries greater than 100K?
     */
    @Override
    public boolean salariesGreaterThan(double max) {
        List<Member> salaries = allMembers.stream().filter(member -> member.salary() > max).collect(Collectors.toList());
        return !salaries.isEmpty();
    }

    /**
     * Are there any members of given house?
     */
    @Override
    public boolean anyMembers(House house) {
        List<Member> members = allMembers.stream().filter(member -> member.house() == house).collect(Collectors.toList());
        return !members.isEmpty();
    }

    /**
     * How many members of a given house are there?
     */
    @Override
    public long howMany(House house) {
        return allMembers.stream().filter(member -> member.house() == house).count();
    }

    /**
     * Return the names of a given house as a comma-separated string
     */
    @Override
    public String houseMemberNames(House house) {
        return allMembers.stream().filter(member -> member.house() == house).map(Member::name).collect(Collectors.joining(", "));
    }

    /**
     * Who has the highest salary?
     */
    @Override
    public Optional<Member> highestSalary() {
        return allMembers.stream().max(Comparator.comparing(Member::salary));
    }

    /**
     * Partition members into royalty and non-royalty
     * (note: royalty are KINGs and QUEENs only)
     */
    @Override
    public Map<Boolean, List<Member>> royaltyPartition() {
        Map<Boolean, List<Member>> map_1 = allMembers.stream().collect(Collectors.partitioningBy(member -> member.title() == Title.KING || member.title() == Title.QUEEN));
        return map_1;
    }

    /**
     * Group members into Houses
     */
    @Override
    public Map<House, List<Member>> membersByHouse() {
        Map<House, List<Member>> map_2 = allMembers.stream().collect(Collectors.groupingBy(Member::house));
        return map_2;

    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using counting
     */
    @Override
    public Map<House, Long> numberOfMembersByHouse() {
        Map<House, Long> map_3 = allMembers.stream().collect(Collectors.groupingBy(Member::house, Collectors.counting()));
        return map_3;
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Override
    public Map<House, DoubleSummaryStatistics> houseStats() {
        Map<House, DoubleSummaryStatistics> map_4 = allMembers.stream().collect(Collectors.groupingBy(Member::house, Collectors.summarizingDouble(Member::salary)));
        return map_4;
    }

    /**
     * Get the richest house
     */
    public Map.Entry<House,Double> richestHouse() {
        Map<House, Double> map_5 = allMembers.stream().collect(Collectors.groupingBy(Member::house, Collectors.summingDouble(Member::salary)));
        Map.Entry<House, Double> richestHouse = map_5.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
        return richestHouse;
    }



}
