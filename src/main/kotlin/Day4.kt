class Day4 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        val list = mutableListOf<MutableMap<String, String>>();

        list.add(mutableMapOf());
        for(line in input){
            if(line == ""){
                list.add(mutableMapOf());
            }else{
                line.split(" ").forEach{
                    val split = it.split(":")
                    list.last()[split[0]] = split[1];
                }
            }
        }

        var valids = 0;

        val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid") //, "cid"

        for(passport in list){
            if(requiredFields.all { passport.containsKey(it) }){
                valids++;
            }
        }

        return valids.toString()

    }

    override fun puzzleTwo(input: List<String>): String {

        val list = mutableListOf<MutableMap<String, String>>();

        list.add(mutableMapOf());
        for(line in input){
            if(line == ""){
                list.add(mutableMapOf());
            }else{
                line.split(" ").forEach{
                    val split = it.split(":")
                    list.last()[split[0]] = split[1];
                }
            }
        }

        var valids = 0;

        val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid") //, "cid"

        for(passport in list){
            if(requiredFields.all { passport.containsKey(it) }){
                var valid = (passport["byr"]?.toInt() in 1920..2002)
                valid = valid && (passport["iyr"]?.toInt() in 2010..2020)
                valid = valid && (passport["eyr"]?.toInt() in 2020..2030)
                valid = valid && (passport["hgt"]?.run {
                    val unit = this.substring(this.length - 2)
                    val height = this.substring(0, this.length - 2).toInt()
                    if(unit == "cm"){
                        height in (150..193)
                    }else if(unit == "in"){
                        height in (59..76)
                    }else{
                        false
                    }
                }) ?: false
                valid = valid && "#[0-9a-f]{6}".toRegex().matches(passport["hcl"] ?: "")
                valid = valid && passport["ecl"] in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                valid = valid && "[0-9]{9}".toRegex().matches(passport["pid"] ?: "")


                if(valid)
                    valids++;
            }
        }

        return valids.toString()

    }

}